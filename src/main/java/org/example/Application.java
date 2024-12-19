package org.example;

import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {

        virtualThreadOption();

        createThreadNatif();

    }

    public static void virtualThreadOption(){
        int threadCount = 100_000;
        long startTime = System.currentTimeMillis();

        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            for (int i = 0; i < threadCount; i++) {
                executor.execute(() -> {
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution pour les treads virtuels : " + (endTime - startTime)+" ms.");
    }


    public static void createThreadNatif(){
        int threadCount = 100_000;
        long startTime = System.currentTimeMillis();

        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            for (int i = 0; i < threadCount; i++) {
                Thread thread = new Thread(() -> {
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                });
                thread.start();
            }
        }catch (OutOfMemoryError | Exception e){
            System.out.println("Erreur avec les threads natif");
        }

        long endtime2 = System.currentTimeMillis();
        System.out.println("Total execution pour les threads natifs : " + (endtime2 - startTime+" ms."));
    }

}