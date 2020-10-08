package web.service;

import web.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    @Override
    public List<Car> listCar(List<Car> carList, Integer count) {
        List<Car> list = carList;

        if (count == null || count >= 5){
            return list;
        }
        else list = carList.stream().limit(count).collect(Collectors.toList());
        return list;
    }
}