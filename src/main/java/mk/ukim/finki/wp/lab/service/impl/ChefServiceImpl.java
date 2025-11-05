package mk.ukim.finki.wp.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    @Override
    public List<Chef> listChefs() {
        return this.chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return this.chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {

        Chef chef = chefRepository.findById(chefId).orElse(null);
        Dish dish = dishRepository.findByDishId(dishId);

        if(chef == null ||  dish == null) {
            return null;
        }

        List<Dish> updatedDishes = chef.getDishes();
        updatedDishes.add(dish);

        Chef updatedChef = new Chef(chef.getId(), chef.getFirstName(), chef.getLastName(), chef.getBio(), updatedDishes);

        return chefRepository.save(updatedChef);
    }
}
