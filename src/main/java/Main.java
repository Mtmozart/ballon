import br.com.ballon.domain.expenses.*;
import br.com.ballon.domain.user.Admin;
import br.com.ballon.domain.user.Consumer;
import br.com.ballon.domain.user.Profile;

import java.math.BigDecimal;
import java.time.Year;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin.AdminUserBuilder()
                .withProfile(Profile.ADMIN)
                .withName("Admin User")
                .withEmail("admin@example.com")
                .withPassword("admin123")
                .withIsDeleted(true)
                .build();

        Consumer consumer = new Consumer.ConsumerBuilder()
                .withProfile(Profile.CONSUMER)
                .withName("Consumer User")
                .withEmail("consumer@example.com")
                .withPassword("consumer123")
                .build();
        Category category = new Category(1, CategoryEnum.DISCRETIONARY);

        SubCategory subCategory = new SubCategory.Builder()
                .categoryId(category.getId())
                .userId(consumer.getId())
                .name("sub")
                .build();
        Expense expense = new Expense.Builder()
                .withMonth(Months.JANUARY)
                .withYear(Year.of(2023))
                .withTitle("Aluguel")
                .withValue(new BigDecimal("1500.00"))
                .withCategory(category)
                .withConsumer(consumer)
                .build();
        Expense expense2 = new Expense.Builder()
                .withMonth(Months.JANUARY)
                .withYear(Year.of(2023))
                .withTitle("Aluguel")
                .withValue(new BigDecimal("1500.00"))
                .withCategory(category)
                .withConsumer(consumer)
                .build();

        Expense expense3 = new Expense.Builder()
                .withMonth(Months.JANUARY)
                .withYear(Year.of(2023))
                .withTitle("Aluguel")
                .withValue(new BigDecimal("1500.00"))
                .withCategory(category)
                .withSubCategory(subCategory)
                .withConsumer(consumer)
                .build();

        consumer.addExpense(expense);
        consumer.addExpense(expense2);
        consumer.addExpense(expense3);

        System.out.println(consumer.toString());
    }


}
