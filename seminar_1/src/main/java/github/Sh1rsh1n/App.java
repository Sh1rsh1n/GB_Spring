package github.Sh1rsh1n;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import github.Sh1rsh1n.pojo.Address;
import github.Sh1rsh1n.pojo.Person;

import java.util.Arrays;


public class App 
{
    public static void main( String[] args )
    {
        // создаем объект Person
        Person person1 = new Person("Alexey", "Shirshin", 37, new Address("Moscow", "Aviamotornaya", 13), "alexey@mail.ru", Arrays.asList("+79001112200", "+79001112233"));
        Person person2 = new Person("Denis", "Ivanov", 27, new Address("Smolensk", "Stroitelei", 10),"denis@google.com", Arrays.asList("+79101112233"));
        Person person3 = new Person("Pavel", "popov", 15, new Address("Orel", "Stroitelei", 25),"pavel@google.com", Arrays.asList("+79101112233"));

        // создаем объект Gson
        Gson gson1 = new Gson();
        Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gson3 = gsonBuilder.create();

        // сериализуем объект класса Person в JSON-формат, получаем в виде строки
        String personToJson1 = gson1.toJson(person1);
        String personToJson2 = gson2.toJson(person2);
        String personToJson3 = gson3.toJson(person3);

        // десериализуем строку в формате JSON в объект класса Person
        Person JsonToPerson1 = gson1.fromJson(personToJson1, Person.class);

        //выводим результат для проверки сериализации в JSON
        System.out.printf("Simple JSON: %s\n", personToJson1);
        System.out.printf("\nPretty JSON: %s\n", personToJson2);
        System.out.printf("\nJSON without expose field: %s\n", personToJson3);

        // проверяем объекты исходный и тот что после десериализации
        System.out.printf("\nperson1 equals JsonToPerson1: %s\n", person1.equals(JsonToPerson1));

        // объект после десериализации
        System.out.println(person1);
    }
}
