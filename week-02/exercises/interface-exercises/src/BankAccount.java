public class BankAccount implements MoneyStorage{

    private double balance;
    private String accountNumber;

    public BankAccount(double startingBalance, String accountNumber) {
        this.balance = startingBalance;
        this.accountNumber = accountNumber;
    }


    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {return String.format("Bank Account #%s", accountNumber);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public double withdraw(double amount) {
        if (amount <= 0) {
            return 0;
        }

        double result = Math.min(amount, balance + 25);
        balance -= result;
        return result;
    }
}
