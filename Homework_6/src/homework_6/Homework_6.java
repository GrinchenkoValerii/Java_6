/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package homework_6;

import java.util.Scanner;

/**
 *
 * @author valera
 */
public class Homework_6 {
    public static void main(String[] args) {
        System.out.println("Работу выполнил Гринченко Валерий, РИБО-01-21, Вариант №1" + "\n");

        Thread thread1 = new Thread(new MyRunnable(), "Thread-0");
        Thread thread2 = new Thread(new MyRunnable(), "Thread-1");
        Thread thread3 = new Thread(new MyRunnable(), "Thread-2");

        thread1.start();
        thread2.start();
        thread3.start();

        new Scanner(System.in).nextLine();

        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
    }

    static class MyRunnable implements Runnable {
        private static int currentThread = 0;
        private int threadId;

        public MyRunnable() {
            threadId = currentThread++;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (MyRunnable.class) {
                    try {
                        while (threadId != currentThread % 3) {
                            MyRunnable.class.wait();
                        }
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                        currentThread++;
                        MyRunnable.class.notifyAll();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
    }
}