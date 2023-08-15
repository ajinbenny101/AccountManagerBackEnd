package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.model.Streams;
import com.fdmgroup.springauth.repository.StreamsRepository;

@Service
public class StreamsService {
	private final StreamsRepository streamRepo;
	
	public StreamsService(StreamsRepository streamRepo) {
		super();
		this.streamRepo = streamRepo;
	}

	public List<Streams> allStreams() {
		return streamRepo.findAll();
	}

	public Streams getStreamByStreamCode(String streamCode) {
		Optional<Streams> optionalStream = streamRepo.findById(streamCode);
		if (optionalStream.isPresent()) {
			return optionalStream.get();
		}
		else return null;
	}

	public Streams updateStream(Streams stream) {
		if (streamRepo.existsById(stream.getStreamCode())) {
			return streamRepo.save(stream);
		}
		return null;
	}

	public Streams addStream(Streams stream) {
		if (streamRepo.existsById(stream.getStreamCode())) {
			
		}
		return streamRepo.save(stream);
	}

	public void deleteByStreamCode(String id) {
		if (streamRepo.existsById(id)) {
			streamRepo.deleteById(id);
		}
	}

}
