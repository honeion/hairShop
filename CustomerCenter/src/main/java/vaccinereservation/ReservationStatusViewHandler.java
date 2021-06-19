package vaccinereservation;

import vaccinereservation.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationStatusViewHandler {


    @Autowired
    private ReservationStatusRepository reservationStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenVaccineReserved_then_CREATE_1 (@Payload VaccineReserved vaccineReserved) {
        try {

            if (!vaccineReserved.validate()) return;

            // view 객체 생성
            ReservationStatus reservationStatus = new ReservationStatus();
            // view 객체에 이벤트의 Value 를 set 함
            reservationStatus.setReservationId(vaccineReserved.getId());
            reservationStatus.setReservationStatus(vaccineReserved.getReservationStatus());
            reservationStatus.setUserName(vaccineReserved.getUserName());
            reservationStatus.setUserPhone(vaccineReserved.getUserPhone());
            // view 레파지 토리에 save
            reservationStatusRepository.save(reservationStatus);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenCanceledVaccineReservation_then_UPDATE_1(@Payload CanceledVaccineReservation canceledVaccineReservation) {
        try {
            if (!canceledVaccineReservation.validate()) return;
                // view 객체 조회
            List<ReservationStatus> reservationStatusList = reservationStatusRepository.findByReservationId(canceledVaccineReservation.getId());
            for(ReservationStatus reservationStatus : reservationStatusList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reservationStatus.setReservationStatus(canceledVaccineReservation.getReservationStatus());
                // view 레파지 토리에 save
                reservationStatusRepository.save(reservationStatus);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenVaccineAssigned_then_UPDATE_2(@Payload VaccineAssigned vaccineAssigned) {
        try {
            if (!vaccineAssigned.validate()) return;
                // view 객체 조회
            List<ReservationStatus> reservationStatusList = reservationStatusRepository.findByVaccineId(vaccineAssigned.getId());
            for(ReservationStatus reservationStatus : reservationStatusList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reservationStatus.setVaccineName(vaccineAssigned.getVaccineName());
                reservationStatus.setVaccineDate(vaccineAssigned.getVaccineDate());
                reservationStatus.setVaccineValidationDate(vaccineAssigned.getVaccineValidationDate());
                reservationStatus.setReservationStatus(vaccineAssigned.getReservationStatus());
                // view 레파지 토리에 save
                reservationStatusRepository.save(reservationStatus);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenHospitalAssigned_then_UPDATE_3(@Payload HospitalAssigned hospitalAssigned) {
        try {
            if (!hospitalAssigned.validate()) return;
                // view 객체 조회
            List<ReservationStatus> reservationStatusList = reservationStatusRepository.findByHospitalId(hospitalAssigned.getId());
            for(ReservationStatus reservationStatus : reservationStatusList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reservationStatus.setHospitalName(hospitalAssigned.getHospitalName());
                reservationStatus.setHospitalLocation(hospitalAssigned.getHospitalLocation());
                // view 레파지 토리에 save
                reservationStatusRepository.save(reservationStatus);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCanceledVaccineReservation_then_UPDATE_4(@Payload CanceledVaccineReservation canceledVaccineReservation) {
        try {
            if (!canceledVaccineReservation.validate()) return;
                // view 객체 조회
            List<ReservationStatus> reservationStatusList = reservationStatusRepository.findByReservationId(canceledVaccineReservation.getId());
            for(ReservationStatus reservationStatus : reservationStatusList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reservationStatus.setReservationStatus(canceledVaccineReservation.getReservationStatus());
                // view 레파지 토리에 save
                reservationStatusRepository.save(reservationStatus);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCanceledVaccineAssigned_then_UPDATE_5(@Payload CanceledVaccineAssigned canceledVaccineAssigned) {
        try {
            if (!canceledVaccineAssigned.validate()) return;
                // view 객체 조회
            List<ReservationStatus> reservationStatusList = reservationStatusRepository.findByReservationId(canceledVaccineAssigned.getReservationId());
            for(ReservationStatus reservationStatus : reservationStatusList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reservationStatus.setReservationStatus(canceledVaccineAssigned.getReservationStatus());
                // view 레파지 토리에 save
                reservationStatusRepository.save(reservationStatus);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}