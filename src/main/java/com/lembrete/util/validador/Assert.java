package com.lembrete.util.validador;

import java.util.List;
import java.util.function.Supplier;

public class Assert {


    public static void isNotEmpty(List list, Supplier<RuntimeException> exception){
        if(list.isEmpty()){
            throw exception.get();
        }
    }


}
