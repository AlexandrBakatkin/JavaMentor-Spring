package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    private final CarService carService = new CarServiceImpl();

    @GetMapping(value = "/cars")
    public String printCars(@RequestParam(value = "count", required = false) Integer count,
                            ModelMap model){
        List<Car> carList = new ArrayList <>();
        carList.add(new Car("Tesla", "Model S", 1111));
        carList.add(new Car("Tesla", "Model E", 2222));
        carList.add(new Car("Tesla", "Model X", 3333));
        carList.add(new Car("Tesla", "Model Y", 4444));
        carList.add(new Car("NIVA", "2121", 5555));
        model.addAttribute("carsList", carService.listCar(carList, count));
        return "cars";
    }
}

//TODO: Задание:
//1. Создайте еще один контроллер, замаппленный на /cars.
//2. Создайте модель Car с тремя произвольными полями.
//3. Создайте список из 5 машин.
//3. Создайте сервис с методом, который будет возвращать указанное число машин из созданного списка.
//4. Создайте страницу cars.html. Реализуйте создание таблицы с машинами из сервиса с помощью thymeleaf.
//5. При запросе /cars выводить весь список. При запросе /cars?count=2 должен отобразиться список из 2 машин,
//при /cars?count=3 - из 3, и тд. При count ≥ 5 выводить весь список машин.