package org.example;

public class TransferThread implements Runnable{
    private Account transferAccount;
    private Account receiveAccount;
    private int amount;

    public TransferThread(Account transferAccount, Account receiveAccount, int amount) {
        this.transferAccount = transferAccount;
        this.receiveAccount = receiveAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        synchronized (transferAccount) { // Đồng bộ hóa từ tài khoản gửi
            if (transferAccount.getBalance() >= amount) {
                transferAccount.withdraw(amount);

                synchronized (receiveAccount) { // Đồng bộ hóa tới tài khoản nhận
                    receiveAccount.deposit(amount);
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " attempted to transfer: " + amount + ", but insufficient balance.");
            }
        }
    }
}
