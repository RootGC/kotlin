/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.js.test.wasm.semantics;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("js/js.translator/testData/wasmBox")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class IrWasmBoxWasmTestGenerated extends AbstractIrWasmBoxWasmTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest0(this::doTest, TargetBackend.WASM, testDataFilePath);
    }

    public void testAllFilesPresentInWasmBox() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("js/js.translator/testData/wasmBox"), Pattern.compile("^([^_](.+))\\.kt$"), null, TargetBackend.WASM, true);
    }

    @TestMetadata("basicTypes.kt")
    public void testBasicTypes() throws Exception {
        runTest("js/js.translator/testData/wasmBox/basicTypes.kt");
    }

    @TestMetadata("interfaceMethodCalls.kt")
    public void testInterfaceMethodCalls() throws Exception {
        runTest("js/js.translator/testData/wasmBox/interfaceMethodCalls.kt");
    }

    @TestMetadata("primitivesOperatos.kt")
    public void testPrimitivesOperatos() throws Exception {
        runTest("js/js.translator/testData/wasmBox/primitivesOperatos.kt");
    }

    @TestMetadata("propertyInitializers.kt")
    public void testPropertyInitializers() throws Exception {
        runTest("js/js.translator/testData/wasmBox/propertyInitializers.kt");
    }

    @TestMetadata("sideEffectOnDispatchReceiver.kt")
    public void testSideEffectOnDispatchReceiver() throws Exception {
        runTest("js/js.translator/testData/wasmBox/sideEffectOnDispatchReceiver.kt");
    }

    @TestMetadata("simpleClass.kt")
    public void testSimpleClass() throws Exception {
        runTest("js/js.translator/testData/wasmBox/simpleClass.kt");
    }

    @TestMetadata("typeCheck.kt")
    public void testTypeCheck() throws Exception {
        runTest("js/js.translator/testData/wasmBox/typeCheck.kt");
    }

    @TestMetadata("virtualCalls.kt")
    public void testVirtualCalls() throws Exception {
        runTest("js/js.translator/testData/wasmBox/virtualCalls.kt");
    }

    @TestMetadata("js/js.translator/testData/wasmBox/controlStructures")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class ControlStructures extends AbstractIrWasmBoxWasmTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest0(this::doTest, TargetBackend.WASM, testDataFilePath);
        }

        public void testAllFilesPresentInControlStructures() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("js/js.translator/testData/wasmBox/controlStructures"), Pattern.compile("^([^_](.+))\\.kt$"), null, TargetBackend.WASM, true);
        }

        @TestMetadata("compareBoxedIntegerToZero.kt")
        public void testCompareBoxedIntegerToZero() throws Exception {
            runTest("js/js.translator/testData/wasmBox/controlStructures/compareBoxedIntegerToZero.kt");
        }

        @TestMetadata("doWhile.kt")
        public void testDoWhile() throws Exception {
            runTest("js/js.translator/testData/wasmBox/controlStructures/doWhile.kt");
        }

        @TestMetadata("kt3203_1.kt")
        public void testKt3203_1() throws Exception {
            runTest("js/js.translator/testData/wasmBox/controlStructures/kt3203_1.kt");
        }

        @TestMetadata("kt3273.kt")
        public void testKt3273() throws Exception {
            runTest("js/js.translator/testData/wasmBox/controlStructures/kt3273.kt");
        }

        @TestMetadata("kt3280.kt")
        public void testKt3280() throws Exception {
            runTest("js/js.translator/testData/wasmBox/controlStructures/kt3280.kt");
        }

        @TestMetadata("kt416.kt")
        public void testKt416() throws Exception {
            runTest("js/js.translator/testData/wasmBox/controlStructures/kt416.kt");
        }

        @TestMetadata("kt9022Throw.kt")
        public void testKt9022Throw() throws Exception {
            runTest("js/js.translator/testData/wasmBox/controlStructures/kt9022Throw.kt");
        }

        @TestMetadata("js/js.translator/testData/wasmBox/controlStructures/breakContinueInExpressions")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class BreakContinueInExpressions extends AbstractIrWasmBoxWasmTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest0(this::doTest, TargetBackend.WASM, testDataFilePath);
            }

            public void testAllFilesPresentInBreakContinueInExpressions() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("js/js.translator/testData/wasmBox/controlStructures/breakContinueInExpressions"), Pattern.compile("^([^_](.+))\\.kt$"), null, TargetBackend.WASM, true);
            }

            @TestMetadata("breakInExpr.kt")
            public void testBreakInExpr() throws Exception {
                runTest("js/js.translator/testData/wasmBox/controlStructures/breakContinueInExpressions/breakInExpr.kt");
            }
        }

        @TestMetadata("js/js.translator/testData/wasmBox/controlStructures/returnsNothing")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class ReturnsNothing extends AbstractIrWasmBoxWasmTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest0(this::doTest, TargetBackend.WASM, testDataFilePath);
            }

            public void testAllFilesPresentInReturnsNothing() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("js/js.translator/testData/wasmBox/controlStructures/returnsNothing"), Pattern.compile("^([^_](.+))\\.kt$"), null, TargetBackend.WASM, true);
            }

            @TestMetadata("ifElse.kt")
            public void testIfElse() throws Exception {
                runTest("js/js.translator/testData/wasmBox/controlStructures/returnsNothing/ifElse.kt");
            }

            @TestMetadata("propertyGetter.kt")
            public void testPropertyGetter() throws Exception {
                runTest("js/js.translator/testData/wasmBox/controlStructures/returnsNothing/propertyGetter.kt");
            }

            @TestMetadata("when.kt")
            public void testWhen() throws Exception {
                runTest("js/js.translator/testData/wasmBox/controlStructures/returnsNothing/when.kt");
            }
        }
    }

    @TestMetadata("js/js.translator/testData/wasmBox/number")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Number extends AbstractIrWasmBoxWasmTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest0(this::doTest, TargetBackend.WASM, testDataFilePath);
        }

        public void testAllFilesPresentInNumber() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("js/js.translator/testData/wasmBox/number"), Pattern.compile("^([^_](.+))\\.kt$"), null, TargetBackend.WASM, true);
        }

        @TestMetadata("assignmentIntOverflow.kt")
        public void testAssignmentIntOverflow() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/assignmentIntOverflow.kt");
        }

        @TestMetadata("byteAndShortConversions.kt")
        public void testByteAndShortConversions() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/byteAndShortConversions.kt");
        }

        @TestMetadata("conversionsWithTruncation.kt")
        public void testConversionsWithTruncation() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/conversionsWithTruncation.kt");
        }

        @TestMetadata("conversionsWithoutTruncation.kt")
        public void testConversionsWithoutTruncation() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/conversionsWithoutTruncation.kt");
        }

        @TestMetadata("division.kt")
        public void testDivision() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/division.kt");
        }

        @TestMetadata("doubleConversions.kt")
        public void testDoubleConversions() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/doubleConversions.kt");
        }

        @TestMetadata("hashCode.kt")
        public void testHashCode() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/hashCode.kt");
        }

        @TestMetadata("hexadecimalConstant.kt")
        public void testHexadecimalConstant() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/hexadecimalConstant.kt");
        }

        @TestMetadata("incDecOptimization.kt")
        public void testIncDecOptimization() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/incDecOptimization.kt");
        }

        @TestMetadata("intConversions.kt")
        public void testIntConversions() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/intConversions.kt");
        }

        @TestMetadata("intDivFloat.kt")
        public void testIntDivFloat() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/intDivFloat.kt");
        }

        @TestMetadata("intIncDecOverflow.kt")
        public void testIntIncDecOverflow() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/intIncDecOverflow.kt");
        }

        @TestMetadata("intOverflow.kt")
        public void testIntOverflow() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/intOverflow.kt");
        }

        @TestMetadata("kt2342.kt")
        public void testKt2342() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/kt2342.kt");
        }

        @TestMetadata("longBinaryOperations.kt")
        public void testLongBinaryOperations() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/longBinaryOperations.kt");
        }

        @TestMetadata("longBitOperations.kt")
        public void testLongBitOperations() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/longBitOperations.kt");
        }

        @TestMetadata("longCompareToIntrinsic.kt")
        public void testLongCompareToIntrinsic() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/longCompareToIntrinsic.kt");
        }

        @TestMetadata("longEqualsIntrinsic.kt")
        public void testLongEqualsIntrinsic() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/longEqualsIntrinsic.kt");
        }

        @TestMetadata("longHashCode.kt")
        public void testLongHashCode() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/longHashCode.kt");
        }

        @TestMetadata("longUnaryOperations.kt")
        public void testLongUnaryOperations() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/longUnaryOperations.kt");
        }

        @TestMetadata("mixedTypesOverflow.kt")
        public void testMixedTypesOverflow() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/mixedTypesOverflow.kt");
        }

        @TestMetadata("numberCompareTo.kt")
        public void testNumberCompareTo() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/numberCompareTo.kt");
        }

        @TestMetadata("numberConversions.kt")
        public void testNumberConversions() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/numberConversions.kt");
        }

        @TestMetadata("numberEquals.kt")
        public void testNumberEquals() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/numberEquals.kt");
        }

        @TestMetadata("numberIncDec.kt")
        public void testNumberIncDec() throws Exception {
            runTest("js/js.translator/testData/wasmBox/number/numberIncDec.kt");
        }
    }

    @TestMetadata("js/js.translator/testData/wasmBox/passedCommonTests")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class PassedCommonTests extends AbstractIrWasmBoxWasmTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest0(this::doTest, TargetBackend.WASM, testDataFilePath);
        }

        public void testAllFilesPresentInPassedCommonTests() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("js/js.translator/testData/wasmBox/passedCommonTests"), Pattern.compile("^([^_](.+))\\.kt$"), null, TargetBackend.WASM, true);
        }

        @TestMetadata("js/js.translator/testData/wasmBox/passedCommonTests/primitiveTypes")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class PrimitiveTypes extends AbstractIrWasmBoxWasmTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest0(this::doTest, TargetBackend.WASM, testDataFilePath);
            }

            public void testAllFilesPresentInPrimitiveTypes() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("js/js.translator/testData/wasmBox/passedCommonTests/primitiveTypes"), Pattern.compile("^([^_](.+))\\.kt$"), null, TargetBackend.WASM, true);
            }

            @TestMetadata("ea35963.kt")
            public void testEa35963() throws Exception {
                runTest("js/js.translator/testData/wasmBox/passedCommonTests/primitiveTypes/ea35963.kt");
            }
        }
    }
}
