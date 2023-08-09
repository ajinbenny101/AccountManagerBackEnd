package com.fdmgroup.springauth.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.springauth.model.Streams;
import com.fdmgroup.springauth.service.StreamsService;

@RestController
@RequestMapping("/api/v1/streams")
public class StreamsController {
	private final StreamsService streamsService;

	public StreamsController(StreamsService streamsService) {
		super();
		this.streamsService = streamsService;
	}
	
	@GetMapping
	public ResponseEntity<List<Streams>> getAllStreams(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(streamsService.allStreams());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Streams> getStreamByStreamCode(String streamCode){
		Streams stream = streamsService.getStreamByStreamCode(streamCode);
		if (stream != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(stream);
		} else return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Streams> updateStream(@PathVariable String id, @RequestBody Streams stream){
		stream.setStreamCode(id);
		Streams updatedStream = streamsService.updateStream(stream);
		if (updatedStream != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(stream);
		} else return null;
	}
	
	@PostMapping
	public ResponseEntity<Streams> addStream(@RequestBody Streams stream){
		Streams addedStream = streamsService.addStream(stream);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedStream.getStreamCode())
				.toUri();
		return ResponseEntity
				.created(location)
				.body(addedStream);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStreamByStreamCode(@PathVariable String id){
		streamsService.deleteByStreamCode(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
