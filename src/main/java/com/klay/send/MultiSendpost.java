package com.klay.send;

import java.io.IOException;

public class MultiSendpost  {
    public static void main(String[] args) throws InterruptedException {
//        MyThread thread1=new MyThread(0,4);
//        thread1.start();
//        MyThread thread2=new MyThread(5,9);
//        thread2.start();
//        MyThread thread3=new MyThread(10,14);
//        thread3.start();
//        MyThread thread4=new MyThread(15,19);
//        thread4.start();
//        MyThread thread5=new MyThread(20,24);
//        thread5.start();
//        MyThread thread6=new MyThread(25,29);
//        thread6.start();
//        MyThread thread7=new MyThread(30,34);
//        thread7.start();
//        MyThread thread8=new MyThread(35,39);
//        thread8.start();
//        MyThread thread9=new MyThread(40,44);
//        thread9.start();
//        MyThread thread10=new MyThread(45,50);
//        thread10.start();
//        Thread.sleep(1000);
    }

    public static class MyThread extends Thread{
        private int low;
        private int high;

        public MyThread(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        public void run() {
            try {
                //long id = Thread.currentThread().getId();
                SendPostMain.send(low,high);
               // System.out.println(Thread.currentThread().getName()+"------");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
