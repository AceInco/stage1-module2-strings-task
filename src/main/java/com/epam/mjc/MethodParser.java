package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        MethodSignature methodSignature;

        List<String> signatureNoArguments = List.of(signatureString.substring(0, signatureString.indexOf("(")).split(" "));
        List<String> signatureArguments = List.of(signatureString.substring(signatureString.indexOf("(") + 1, signatureString.indexOf(")")).split(", "));

        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if(signatureArguments.size() == 1 && Objects.equals(signatureArguments.get(0), "")){
            signatureArguments = new ArrayList<>();
        }
        for (String signatureArgument : signatureArguments) {
            arguments.add(new MethodSignature.Argument(signatureArgument.split(" ")[0], signatureArgument.split(" ")[1]));
        }

        if (signatureNoArguments.size() == 3) {
            methodSignature = new MethodSignature(signatureNoArguments.get(2), arguments);
            methodSignature.setAccessModifier(signatureNoArguments.get(0));
            methodSignature.setReturnType(signatureNoArguments.get(1));
        } else {
            methodSignature = new MethodSignature(signatureNoArguments.get(1), arguments);
            methodSignature.setReturnType(signatureNoArguments.get(0));
        }
        return methodSignature;
    }
}
