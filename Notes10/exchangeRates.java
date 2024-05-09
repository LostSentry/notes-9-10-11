import java.util.Map;
import static input.InputUtils.doubleInput;

public class exchangeRates {

    public static void main(String[] args) {

        String url = "https//exchange-rates-1150.herokuapp.com/latest";
        Map<String, Object> queryParameters = Map.of("base", "USD", "symbols", "EUR");

        RateResponse response = Unirest.get(url)
                .queryString(queryParameters)
                .asObject(RateResponse.class)
                .getBody();

        String dateRequested = response.date;
        double rate = response.rates.EUR;
        double amountOfDollars = doubleInput("How many US dollars? ")
        double euroEquivalent = amountOfDollars * rate;
        System.out.println(amountOfDollars + " = " + euroEquivalent);
        System.out.println("date: " + dateRequested + " rate: " + rate);


    }
}

class RateResponse {
    public String base;
    public String date;
    public Rates rates;
}

class Rates {
    public double EUR;
}