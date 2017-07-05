package email.data;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "getActiveUser")
    public static Object[][] getActiveUser() {
        return new Object[][]{
                {Users.getFirstUser()},
                {Users.getSecondUser()}
        };
    }
}