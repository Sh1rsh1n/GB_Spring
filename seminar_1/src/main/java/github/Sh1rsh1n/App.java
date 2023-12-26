package github.Sh1rsh1n;

import com.google.gson.Gson;
import github.Sh1rsh1n.pojo.Person;


public class App 
{
    public static void main( String[] args )
    {
        // создаем объект Person
        Person person = new Person("Alexey", "Shirshin", 37);

        // создаем объект Gson
        Gson gson = new Gson();

        // сериализуем объект класса Person в JSON-формат, получаем в виде строки
        String personToJson = gson.toJson(person);

        // десериализуем строку в формате JSON в объект класса Person
        Person person1 = gson.fromJson(personToJson, Person.class);

        //выводим результат для проверки сериализации в JSON
        System.out.println(personToJson);

        // проверяем объекты исходный и тот что после десериализации
        System.out.println(person1.equals(person));

        // объект после десериализации
        System.out.println(person1);

    }
}
