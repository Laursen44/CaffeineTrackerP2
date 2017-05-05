package p2.b230.caffeinetrackerp2;

public class CaffeineDatabase
{


    public CaffeineDatabase()
    {

    }

    public float returnCaffeineContent(String caffeineType, String amount)
    {
        float caffeineDensity = caffeineDensityFromString(caffeineType);
        float unitAmount = amountFromString(amount);

        float result = caffeineDensity * unitAmount;
        return result;
    }

    private float amountFromString(String amount)
    {
        float givenAmount = 0;
        switch(amount)
        {
            case "25ml":
                givenAmount = 25;
                break;
            case "200ml":
                givenAmount = 200;
                break;
            case "250ml":
                givenAmount = 250;
                break;
            case "330ml":
                givenAmount = 330;
                break;
            case "500ml":
                givenAmount = 500;
                break;
            case "660ml":
                givenAmount = 660;
                break;
            case "1000ml":
                givenAmount = 1000;
                break;
            case "1 unit":
                givenAmount = 1;
                break;
        }

        return givenAmount;
    }

    private float caffeineDensityFromString(String caffeineType)
    {
        float caffeinePerUnit = 0;

        if(caffeineType == null)
        {
            caffeinePerUnit = 0f;
            return caffeinePerUnit;
        }
        switch(caffeineType)
        {
            case "Coca Cola":
                caffeinePerUnit = 0.1f;
                break;
            case "Faxe Kondi":
                caffeinePerUnit = 0f;
                break;
            case "Fanta":
                caffeinePerUnit = 0f;
                break;
            case "Sprite":
                caffeinePerUnit = 0f;
                break;
            case "Pepsi":
                caffeinePerUnit = 0.1f;
                break;
            case "Black Coffee":
                caffeinePerUnit = 0.4f;
                break;
            case "Instant Coffee":
                caffeinePerUnit = 0.2f;
                break;
            case "Espresso":
                caffeinePerUnit = 1.5f;
                break;
            case "Macchiato":
                caffeinePerUnit = 0.4f;
                break;
            case "Green Tea":
                caffeinePerUnit = 0.1f;
                break;
            case "Black Tea":
                caffeinePerUnit = 0.2f;
                break;
            case "Light Tea":
                caffeinePerUnit = 0.05f;
                break;
            case "Cammellia":
                caffeinePerUnit = 0.1f;
                break;
            case "Chamomile":
                caffeinePerUnit = 0.1f;
                break;
            case "Red Bull":
                caffeinePerUnit = 0.3f;
                break;
            case "Monster":
                caffeinePerUnit = 0.3f;
                break;
            case "Cult":
                caffeinePerUnit = 0.3f;
                break;
            case "Faxe Booster":
                caffeinePerUnit = 0.3f;
                break;
            case "Burn":
                caffeinePerUnit = 0.3f;
                break;
            case "Rockstar":
                caffeinePerUnit = 0.3f;
                break;
            case "Caff pill":
                caffeinePerUnit = 200f;
                break;
            case "Pre-Workout":
                caffeinePerUnit = 140f;
                break;
            case "Chocolate Dark":
                caffeinePerUnit = 0.02f;
                break;
            case "Gum":
                caffeinePerUnit = 50f;
                break;
        }

        return caffeinePerUnit;
    }

}
