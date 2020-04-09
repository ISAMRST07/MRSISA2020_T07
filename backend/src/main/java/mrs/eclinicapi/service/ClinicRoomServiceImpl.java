package mrs.eclinicapi.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.repository.ClinicRoomRepository;


@Service
public class ClinicRoomServiceImpl implements ClinicRoomService {

    @Autowired
    private ClinicRoomRepository clinicRoomRepository;

	@Override
	public List<ClinicRoom> getAllClinicRooms() {
		return clinicRoomRepository.getAllClinicRooms();
	}

	@Override
	public void remove(int id) {
		clinicRoomRepository.deleteById(id);
	}

	@Override
	public ClinicRoom findOneClinicRoom(int id) {
		return clinicRoomRepository.findById(id);
	}
}