package com.upload.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;
import com.mongodb.Function;
import com.upload.util.UtilHelper;

import ch.qos.logback.classic.pattern.Util;
import io.netty.util.Signal;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class Person {

	private Long id;
	private String name;

	Random random = new Random();
	SecureRandom random2 = new SecureRandom();

	public String nextString(int length) {
		return new BigInteger(length * 5, random2).toString(32);
	}

	public Person() {
		super();
		this.id = random.nextLong();
		this.name = nextString(5);
	}

	public Person(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

public class Test {

	private static Flux<Integer> getOrdersOfNumbers() {
		return Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
	}

	private static Flux<Integer> fallBack() {
		return Flux.range(100, 5).delayElements(Duration.ofMillis(100));
	}

	public static Flux<Person> getPerson() {
		return Flux.range(1, 10).map(i -> new Person());
	}

	public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
		return flux -> flux.filter(p -> p.getId() > 5).doOnNext(p -> p.getName().toUpperCase());
	}
	
	private static Stream<String> getMovie(){
		System.out.println("Streaming request got...");
		return Stream.of("Stream1",
				"Stream2",
				"Stream3",
				"Stream4",
				"Stream5",
				"Stream6",
				"Stream7"
				);
	}
	

	public static void main(String[] args) {

		Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

		Disposable filterFlux = flux.filter(e -> e % 2 == 0).subscribe(j -> System.out.println("Sub 1 : " + j));
//		Flux<Integer> filterFlux = (Flux<Integer>) flux.filter(e -> e % 2 == 0).subscribe(j -> System.out.println("Sub 1 : " + j));

		System.out.println("=====================================================");

//		Converting list to flux
		List<String> stringList = Arrays.asList("Rahul", "Vimlesh", "Abhay");
		Flux.fromIterable(stringList).subscribe(i -> System.out.println("List Converted to flux :" + i));

		System.out.println("=====================================================");

//		Converting array into flux
		String[] arrayString = { "R", "V", "A" };
		Flux.fromArray(arrayString).subscribe(i -> System.out.println("Array Converted to flux :" + i));

		System.out.println("=====================================================");

		flux.subscribe(i -> System.out.println("Sub 2 : " + i));

		System.out.println("=====================================================");

		List<Integer> list = List.of(3, 4, 5, 6, 7);
		Stream<Integer> stream = list.stream();
		Flux<Integer> fromStream = Flux.fromStream(() -> list.stream());
		fromStream.subscribe(e -> System.out.println(e));
		System.out.println("=====================================================");
		fromStream.subscribe(e -> System.out.println(e));

		System.out.println("=====================================================");

		Flux.range(2, 7)
		.delayElements(Duration.ofSeconds(1))
		.subscribe(print -> System.out.println("Printing --> " + print)); // here o/p will be 2,3,4,5,6,7,8 because
																				// range(start,count) --> start + 1 ...
																				// till count

		System.out.println("=====================================================");

		Flux.interval(Duration.ofSeconds(1)).subscribe();

		System.out.println("=====================================================");
//		Converting Flux to mono
//		Requirement --> Suppose we have a mono object and we want to pass a flux object to method, then we need to convert mono to flux
		Mono<Integer> mono = Mono.just(1);
		Flux<Integer> convertedFlux = Flux.from(mono);
//		
//		public void doSomething(Flux<Integer> flux) {
//			
//		}

//		System.out.println("=====================================================");

//		Flux to Mono
		Flux<Integer> range = Flux.range(1, 10);
		Mono<Integer> convertedMono = range.next();

//		System.out.println("=====================================================");

//		Flux.generate(SSL -> {
//			
//			for(int i = 0;i < 10;i++) {
//				String name  = "hii" + i;
//				SSL.next(name);
//				if(name.equals("hii3")) {
//					SSL.complete();
//				}
//			}
//			
//			
//		})
//		.subscribe();

		System.out.println("=====================================================");

		getOrdersOfNumbers().timeout(Duration.ofSeconds(2), fallBack()).subscribe();

		System.out.println("=====================================================");

//		getPerson().transform(applyFilterMap()).subscribe();
		
//		Flux<String> movieStream = Flux.fromStream(() -> getMovie())
//				.delayElements(Duration.ofSeconds(2))     // If we let code till here, it will be cold publisher...
//				.share();  // When we use .share() --> cold publisher will get converted to hot publisher...
////				.share() --> Internall uses publish.refCount(1).
//		
//		
//			movieStream.subscribe();
		
		
//		Rather than above code, we can use..
//		Instead of using share, we can use publish().refCount(int) --> it will indicate this much subscriber is required by publisher to emit the data...
//		Flux<String> moviestream = Flux.fromStream(() -> getMovie())
//				.delayElements(Duration.ofSeconds(2))
//				.publish()       // Creates a connectable flux     
//				.refCount(2);   // Here in this case, 2 subscriber is required to publish the data from getMovie();
//		
//		moviestream.subscribe();
		
		
		Flux<String> moviestream = Flux.fromStream(() -> getMovie())
				.delayElements(Duration.ofSeconds(2))
				.publish()       // Creates a connectable flux     
				.autoConnect();   // Here in this case, it will emit data immediately without even wating for any subscriber.
		
		moviestream.subscribe();
		
//		UtilHelper.sleep(10);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			

	}

}
