package com.ferhatayar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferhatayar.dto.DtoCar;
import com.ferhatayar.dto.DtoPayment;
import com.ferhatayar.dto.DtoRental;
import com.ferhatayar.dto.DtoRentalIU;
import com.ferhatayar.dto.DtoUser;
import com.ferhatayar.expection.BaseExpection;
import com.ferhatayar.expection.ErrorMessage;
import com.ferhatayar.expection.MessageType;
import com.ferhatayar.model.Cars;
import com.ferhatayar.model.Payments;
import com.ferhatayar.model.Rentals;
import com.ferhatayar.model.Users;
import com.ferhatayar.repository.CarRepository;
import com.ferhatayar.repository.PaymentRepository;
import com.ferhatayar.repository.RentalRepository;
import com.ferhatayar.repository.UserRepository;
import com.ferhatayar.service.IRentalService;

@Service
public class RentalServiceImpl implements IRentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private Rentals createRental(DtoRentalIU input) {
        Rentals rental = new Rentals();
        BeanUtils.copyProperties(input, rental);

        Cars car = carRepository.findById(input.getCarId())
                .orElseThrow(() -> new BaseExpection(
                        new ErrorMessage(MessageType.CAR_NOT_FOUND, input.getCarId().toString())));
        rental.setCar(car);

        Users user = userRepository.findById(input.getUserId())
                .orElseThrow(() -> new BaseExpection(
                        new ErrorMessage(MessageType.USER_NOT_FOUND, input.getUserId().toString())));
        rental.setUser(user);

        if (input.getPaymentId() != null) {
            Payments payment = paymentRepository.findById(input.getPaymentId())
                    .orElseThrow(() -> new BaseExpection(
                            new ErrorMessage(MessageType.PAYMENT_NOT_FOUND, input.getPaymentId().toString())));
            rental.setPayments(payment);
        }

        return rental;
    }

    @Override
    public DtoRental saveRental(DtoRentalIU input) {
        Rentals savedRental = rentalRepository.save(createRental(input));

        return rentalToDto(savedRental.getId());
    }

    @Override
    public List<DtoRental> getAllRentalList() {
        List<DtoRental> dtoRentalList = new ArrayList<>();
        List<Rentals> rentals = rentalRepository.findAll();

        for (Rentals rental : rentals) {
            dtoRentalList.add(mapRentalToDto(rental));
        }

        return dtoRentalList;
    }

    private DtoRental rentalToDto(Long id) {
        Rentals rental = rentalRepository.findById(id)
                .orElseThrow(() -> new BaseExpection(
                        new ErrorMessage(MessageType.RENTAL_NOT_FOUND, id.toString())));
        return mapRentalToDto(rental);
    }

    private DtoRental mapRentalToDto(Rentals rental) {
        DtoRental dtoRental = new DtoRental();
        BeanUtils.copyProperties(rental, dtoRental);

        if (rental.getCar() != null) {
            DtoCar dtoCar = new DtoCar();
            BeanUtils.copyProperties(rental.getCar(), dtoCar);
            dtoRental.setCar(dtoCar);
        }

        if (rental.getUser() != null) {
            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(rental.getUser(), dtoUser);
            dtoRental.setUser(dtoUser);
        }

        if (rental.getPayments() != null) {
            DtoPayment dtoPayment = new DtoPayment();
            BeanUtils.copyProperties(rental.getPayments(), dtoPayment);
            dtoRental.setPayment(dtoPayment);
        }

        return dtoRental;
    }

    @Override
    public DtoRental getRentalById(Long id) {
        return rentalToDto(id);
    }

    @Override
    @Transactional
    public DtoRental deleteRental(Long id) {
        Rentals rental = rentalRepository.findById(id)
                .orElseThrow(() -> new BaseExpection(
                        new ErrorMessage(MessageType.RENTAL_NOT_FOUND, id.toString())));

        DtoRental dtoRental = mapRentalToDto(rental);
        rentalRepository.delete(rental);

        return dtoRental;
    }

    @Override
    public DtoRental updateRental(Long id, DtoRentalIU input) {
        Rentals rental = rentalRepository.findById(id)
                .orElseThrow(() -> new BaseExpection(
                        new ErrorMessage(MessageType.RENTAL_NOT_FOUND, id.toString())));

        rental.setStartDate(input.getStartDate());
        rental.setEndDate(input.getEndDate());
        rental.setStatus(input.getStatus());
        rental.setTotalPrice(input.getTotalPrice());

        Cars car = carRepository.findById(input.getCarId())
                .orElseThrow(() -> new BaseExpection(
                        new ErrorMessage(MessageType.CAR_NOT_FOUND, input.getCarId().toString())));
        rental.setCar(car);

        Users user = userRepository.findById(input.getUserId())
                .orElseThrow(() -> new BaseExpection(
                        new ErrorMessage(MessageType.USER_NOT_FOUND, input.getUserId().toString())));
        rental.setUser(user);

        if (input.getPaymentId() != null) {
            Payments payment = paymentRepository.findById(input.getPaymentId())
                    .orElseThrow(() -> new BaseExpection(
                            new ErrorMessage(MessageType.PAYMENT_NOT_FOUND, input.getPaymentId().toString())));
            rental.setPayments(payment);
        } else {
            rental.setPayments(null);
        }

        Rentals updatedRental = rentalRepository.save(rental);
        return mapRentalToDto(updatedRental);
    }
}
