package com.valicek.rocket.data;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.valicek.rocket.entity.Rocket;
import com.valicek.rocket.entity.WindDirection;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

	private RocketRepository rocketRepo;
	private WindDirectionRepository windRepo;

	public  MyCommandLineRunner(RocketRepository rocketRepo,
			WindDirectionRepository windRepo) {
		this.rocketRepo = rocketRepo;
		this.windRepo = windRepo;
	}
	
	@Override
	public void run(String... args) throws Exception {
		rocketRepo.save(new Rocket(Long.valueOf(1), "Zircon", "GROUND_GROUND", Double.valueOf(3.1), Double.valueOf(0),Double.valueOf(0),Double.valueOf(0),Integer.valueOf(1500),new Date()));
		rocketRepo.save(new Rocket(Long.valueOf(2), "Avantgard", "GROUND_GROUND", Double.valueOf(3.1), Double.valueOf(0),Double.valueOf(0),Double.valueOf(0),Integer.valueOf(1500),new Date()));
		rocketRepo.save(new Rocket(Long.valueOf(3), "CH-101", "AIR_GROUND", Double.valueOf(3.0), Double.valueOf(0),Double.valueOf(0),Double.valueOf(6),Integer.valueOf(5500),new Date()));
		rocketRepo.save(new Rocket(Long.valueOf(4), "CH-55", "AIR_GROUND", Double.valueOf(3.1), Double.valueOf(0),Double.valueOf(0),Double.valueOf(6),Integer.valueOf(1500),new Date()));
		rocketRepo.save(new Rocket(Long.valueOf(5), "Kinzhal", "AIR_GROUND", Double.valueOf(3.75), Double.valueOf(0),Double.valueOf(0),Double.valueOf(6),Integer.valueOf(3000),new Date()));
		rocketRepo.save(new Rocket(Long.valueOf(6), "Himars", "GROUND_GROUND", Double.valueOf(0.024), Double.valueOf(0),Double.valueOf(0),Double.valueOf(0),Integer.valueOf(480),new Date()));
		rocketRepo.save(new Rocket(Long.valueOf(7), "S-300", "GROUND_GROUND", Double.valueOf(2.9), Double.valueOf(0),Double.valueOf(0),Double.valueOf(0),Integer.valueOf(75),new Date()));
		rocketRepo.save(new Rocket(Long.valueOf(8), "S-300FM", "GROUND_GROUND", Double.valueOf(2.9), Double.valueOf(0),Double.valueOf(0),Double.valueOf(0),Integer.valueOf(150),new Date()));
	
		windRepo.save(new WindDirection(Long.valueOf(1), "NONE"));
		windRepo.save(new WindDirection(Long.valueOf(2), "FRONT"));
		windRepo.save(new WindDirection(Long.valueOf(3), "BACK"));
		windRepo.save(new WindDirection(Long.valueOf(4), "LEFT_SIDE"));
		windRepo.save(new WindDirection(Long.valueOf(5), "RIGHT_SIDE"));
		windRepo.save(new WindDirection(Long.valueOf(6), "LEFT_SIDE_BACK"));
		windRepo.save(new WindDirection(Long.valueOf(7), "RIGHT_SIDE_BACK"));
		windRepo.save(new WindDirection(Long.valueOf(8), "LEFT_SIDE_FRONT"));
		windRepo.save(new WindDirection(Long.valueOf(9), "RIGHT_SIDE_FRONT"));
	}
}
