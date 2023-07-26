package api.steps;
public class BaseSteps {

    public static String getToken() {
        return "a2c66c10baebda9f2488161e869605dbcec1dee51fc61cd1b312d99f3b36";
    }

    public static String getURL() {
        return "http://localhost:80/jsonrpc.php";
    }
    public static String getJsonrpc() {
        return "jsonrpc";
    }
    public String generateRandom(){
        Integer number = (int) (20 + Math.random()*400);
        return String.valueOf(number);
    }

}
