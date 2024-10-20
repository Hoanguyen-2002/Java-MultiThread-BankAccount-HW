package org.example;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        //Khởi tạo số dư tài khoản ban đầu cho mỗi account
        Account account1 = new Account(1_000_000);
        Account account2 = new Account(500_000);

        // Tạo các luồng để thực hiện việc chuyển tiền
        Thread transfer1 = new Thread(new TransferThread(account1, account2, 1_000_001), "Thread-1");
        Thread transfer2 = new Thread(new TransferThread(account2, account1, 10_000), "Thread-2");
        Thread transfer3 = new Thread(new TransferThread(account1, account2, 2_000), "Thread-3");

        // Bắt đầu các luồng
        transfer1.start();
        transfer2.start();
        transfer3.start();

        try {
            transfer1.join();
            transfer2.join();
            transfer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hiển thị số dư cuối cùng của các tài khoản
        System.out.println("Final balance of account1: " + account1.getBalance());
        System.out.println("Final balance of account2: " + account2.getBalance());
    }
}