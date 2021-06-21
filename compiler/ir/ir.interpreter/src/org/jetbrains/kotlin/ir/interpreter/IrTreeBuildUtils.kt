/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.interpreter

import org.jetbrains.kotlin.builtins.PrimitiveType
import org.jetbrains.kotlin.builtins.UnsignedType
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.descriptors.DescriptorVisibility
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.IrBuiltIns
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrFunctionImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.expressions.impl.*
import org.jetbrains.kotlin.ir.interpreter.state.Complex
import org.jetbrains.kotlin.ir.interpreter.state.ExceptionState
import org.jetbrains.kotlin.ir.interpreter.state.Primitive
import org.jetbrains.kotlin.ir.interpreter.state.State
import org.jetbrains.kotlin.ir.symbols.impl.IrClassSymbolImpl
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl
import org.jetbrains.kotlin.ir.symbols.impl.IrVariableSymbolImpl
import org.jetbrains.kotlin.ir.types.*
import org.jetbrains.kotlin.ir.util.defaultType
import org.jetbrains.kotlin.ir.util.render
import org.jetbrains.kotlin.name.Name

internal val TEMP_CLASS_FOR_INTERPRETER = object : IrDeclarationOriginImpl("TEMP_CLASS_FOR_INTERPRETER") {}
internal val TEMP_FUNCTION_FOR_INTERPRETER = object : IrDeclarationOriginImpl("TEMP_FUNCTION_FOR_INTERPRETER") {}

fun Any?.toIrConstOrNull(irType: IrType, startOffset: Int = UNDEFINED_OFFSET, endOffset: Int = UNDEFINED_OFFSET): IrConst<*>? {
    if (this == null) return IrConstImpl.constNull(startOffset, endOffset, irType)

    val constType = irType.makeNotNull()
    return when (irType.getPrimitiveType()) {
        PrimitiveType.BOOLEAN -> IrConstImpl.boolean(startOffset, endOffset, constType, this as Boolean)
        PrimitiveType.CHAR -> IrConstImpl.char(startOffset, endOffset, constType, this as Char)
        PrimitiveType.BYTE -> IrConstImpl.byte(startOffset, endOffset, constType, (this as Number).toByte())
        PrimitiveType.SHORT -> IrConstImpl.short(startOffset, endOffset, constType, (this as Number).toShort())
        PrimitiveType.INT -> IrConstImpl.int(startOffset, endOffset, constType, (this as Number).toInt())
        PrimitiveType.FLOAT -> IrConstImpl.float(startOffset, endOffset, constType, (this as Number).toFloat())
        PrimitiveType.LONG -> IrConstImpl.long(startOffset, endOffset, constType, (this as Number).toLong())
        PrimitiveType.DOUBLE -> IrConstImpl.double(startOffset, endOffset, constType, (this as Number).toDouble())
        null -> when (constType.getUnsignedType()) {
            UnsignedType.UBYTE -> IrConstImpl.byte(startOffset, endOffset, constType, (this as Number).toByte())
            UnsignedType.USHORT -> IrConstImpl.short(startOffset, endOffset, constType, (this as Number).toShort())
            UnsignedType.UINT -> IrConstImpl.int(startOffset, endOffset, constType, (this as Number).toInt())
            UnsignedType.ULONG -> IrConstImpl.long(startOffset, endOffset, constType, (this as Number).toLong())
            null -> when {
                constType.isString() -> IrConstImpl.string(startOffset, endOffset, constType, this as String)
                else -> null
            }
        }
    }
}

fun Any?.toIrConst(irType: IrType, startOffset: Int = UNDEFINED_OFFSET, endOffset: Int = UNDEFINED_OFFSET): IrConst<*> =
    toIrConstOrNull(irType, startOffset, endOffset)
        ?: throw UnsupportedOperationException("Unsupported const element type ${irType.makeNotNull().render()}")

fun Any?.toIrConst(
    irType: IrType, irBuiltIns: IrBuiltIns,
    startOffset: Int = UNDEFINED_OFFSET, endOffset: Int = UNDEFINED_OFFSET
): IrConst<*> =
    toIrConstOrNull(irType, startOffset, endOffset) ?: run {
        if (irType == irBuiltIns.stringType) IrConstImpl.string(startOffset, endOffset, irType.makeNotNull(), this as String)
        else throw UnsupportedOperationException("Unsupported const element type ${irType.makeNotNull().render()}")
    }

internal fun State.toIrExpression(expression: IrExpression): IrExpression {
    val start = expression.startOffset
    val end = expression.endOffset
    val type = expression.type.makeNotNull()
    return when (this) {
        is Primitive<*> ->
            when {
                this.value == null -> this.value.toIrConst(type, start, end)
                type.isPrimitiveType() || type.isString() -> this.value.toIrConst(type, start, end)
                else -> expression // TODO support for arrays
            }
        is ExceptionState -> {
            IrErrorExpressionImpl(expression.startOffset, expression.endOffset, expression.type, "\n" + this.getFullDescription())
        }
        is Complex -> {
            val stateType = this.irClass.defaultType
            when {
                stateType.isUnsignedType() -> (this.fields.single().state as Primitive<*>).value.toIrConst(type, start, end)
                else -> expression
            }
        }
        else -> expression // TODO support
    }
}

internal fun IrFunction.createCall(): IrCall {
    this as IrSimpleFunction
    return IrCallImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, returnType, symbol, typeParameters.size, valueParameters.size)
}

internal fun IrConstructor.createConstructorCall(): IrConstructorCall {
    return IrConstructorCallImpl.fromSymbolOwner(returnType, symbol)
}

internal fun IrValueDeclaration.createGetValue(): IrGetValue {
    return IrGetValueImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, this.type, this.symbol)
}

internal fun IrValueDeclaration.createTempVariable(): IrVariable {
    return IrVariableImpl(
        UNDEFINED_OFFSET, UNDEFINED_OFFSET, IrDeclarationOrigin.IR_TEMPORARY_VARIABLE, IrVariableSymbolImpl(),
        this.name, this.type, isVar = false, isConst = false, isLateinit = false
    )
}

internal fun IrClass.createGetObject(): IrGetObjectValue {
    return IrGetObjectValueImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, this.defaultType, this.symbol)
}

internal fun IrFunction.createReturn(value: IrExpression): IrReturn {
    return IrReturnImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, this.returnType, this.symbol, value)
}

internal fun createTempFunction(
    name: Name,
    type: IrType,
    origin: IrDeclarationOrigin = TEMP_FUNCTION_FOR_INTERPRETER,
    visibility: DescriptorVisibility = DescriptorVisibilities.PUBLIC
): IrSimpleFunction {
    return IrFunctionImpl(
        UNDEFINED_OFFSET, UNDEFINED_OFFSET, origin, IrSimpleFunctionSymbolImpl(), name, visibility, Modality.FINAL, type,
        isInline = false, isExternal = false, isTailrec = false, isSuspend = false, isOperator = true, isInfix = false, isExpect = false
    )
}

internal fun createTempClass(name: Name, origin: IrDeclarationOrigin = TEMP_CLASS_FOR_INTERPRETER): IrClass {
    return IrFactoryImpl.createClass(
        UNDEFINED_OFFSET, UNDEFINED_OFFSET, origin, IrClassSymbolImpl(), name,
        ClassKind.CLASS, DescriptorVisibilities.PRIVATE, Modality.FINAL
    )
}

internal fun List<IrStatement>.wrapWithBlockBody(): IrBlockBody {
    return IrBlockBodyImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, this)
}

internal fun IrFunctionAccessExpression.shallowCopy(copyTypeArguments: Boolean = true): IrFunctionAccessExpression {
    return when (this) {
        is IrCall -> IrCallImpl.fromSymbolOwner(startOffset, endOffset, type, symbol)
        is IrConstructorCall -> IrConstructorCallImpl.fromSymbolOwner(type, symbol)
        is IrDelegatingConstructorCall -> IrDelegatingConstructorCallImpl.fromSymbolOwner(UNDEFINED_OFFSET, UNDEFINED_OFFSET, type, symbol)
        is IrEnumConstructorCall ->
            IrEnumConstructorCallImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, type, symbol, typeArgumentsCount, valueArgumentsCount)
        else -> TODO("Expression $this cannot be copied")
    }.apply {
        if (copyTypeArguments) {
            (0 until this@shallowCopy.typeArgumentsCount).forEach { this.putTypeArgument(it, this@shallowCopy.getTypeArgument(it)) }
        }
    }
}

internal fun IrFunctionAccessExpression.copyArgsInto(newCall: IrFunctionAccessExpression) {
    newCall.dispatchReceiver = this.dispatchReceiver
    newCall.extensionReceiver = this.extensionReceiver
    (0 until this.valueArgumentsCount)
        .mapNotNull { this.getValueArgument(it) }
        .forEachIndexed { i, arg -> newCall.putValueArgument(i, arg) }
}