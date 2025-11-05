package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init(){

        dishes.add(new Dish("d1", "Pasta", "Italian", 25));
        dishes.add(new Dish("d2", "Tacos", "Mexico", 30));
        dishes.add(new Dish("d3", "Musaka", "Macedonia", 40));
        dishes.add(new Dish("d4", "Burger", "USA", 15));
        dishes.add(new Dish("d5", "Gyro", "Greece", 20));


        chefs.add(new Chef(1L, "Petko", "Petkov", "petkoBio", new ArrayList<>()));
        chefs.add(new Chef(2L, "Marko", "Markov", "markoBio", new ArrayList<>()));
        chefs.add(new Chef(3L, "Trajce", "Trajcev", "trajceBio", new ArrayList<>()));
        chefs.add(new Chef(4L, "Simon", "Simonov", "simonBio", new ArrayList<>()));
        chefs.add(new Chef(5L, "Vasko", "Vaskov", "vaskoBio", new ArrayList<>()));
    }
}
