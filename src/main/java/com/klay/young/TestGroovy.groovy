package com.klay.young

class TestGroovy {
    static main(args){
//        0.upto(4) {println("$it")}
        //0.step(8,2){println("$it")}
        def company="bat";
//        if (company.toString()?.)
        def say={print it}
        say "Hello "
        def power={int x,int y->
            return Math.pow(x,y)
        }
        println power(2,4)
    }

}
