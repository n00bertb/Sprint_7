package praktikum.sprint_7.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderActions orderActions;
    private int track;
    private String firstName = "Antuan";
    private String lastName = "Graduhin";
    private String address = "SPB";
    private String metroStation = "Akademicheskaia";
    private String phone = "88005553535";
    private int rentTime = 4;
    private String deliveryDate = "2023.07.01";
    private String comment = "Какой то текст";
    private String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters()
    public static Object[][] orderColor() {
        return new Object[][]{
                {new String[]{}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK"}},
                {new String[]{"BLACK", "GREY"}},
        };
    }

    @Before
    public void setUp() {
        orderActions = new OrderActions();
    }

    @Test
    @DisplayName("Заказ самокатов всех цветов")
    @Description("Когда создаёшь заказ можно совсем не указывать цвет самоката или можно указать один из цветов — BLACK или GREY или даже можно указать оба цвета")
    public void orderTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = orderActions.create(order);
        response.assertThat().log().all().statusCode(201).body("track", is(notNullValue()));
        track = response.extract().path("track");
    }

    @After
    public void tearDown() {
        orderActions.delete(track);
    }
}
