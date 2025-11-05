package mk.ukim.finki.wp.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public List<Dish> listDishes() {
        return this.dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return this.dishRepository.findByDishId(dishId);
    }
}
