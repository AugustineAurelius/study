package org.example.HW8;

import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HW8Main {
    public static void main(String[] args) throws Throwable {


        List<Streamer> streamers = List.of(xQc, mira, lenaGol0vach, rhinoSpiritX,
                amouranth, itPedia, metalyst, deferPanic, evrone, lofiGirl);

//    1) Найти всех стримеров старше 21 года и с более 15к подсписчиков на платформе twitch
        System.out.println(streamers.stream().filter(s -> s.getAge() > 21 &&
                s.getSubscribers() > 15000 && s.getPlatforms().contains(twitch)).toList());

//    2) Найти самого молодого стримера на незаболкированной платформе
        System.out.println(streamers.stream()
                .filter(s -> s.getPlatforms().stream()
                        .noneMatch(Platform::isRoscomnadzored)
                )
                .min(Comparator.comparingInt(Streamer::getAge)).get());

//    3) Получить Map<Name(String), PlatfromName(String)> для всех стримеров
        System.out.println(
                streamers.stream().collect(Collectors
                        .toMap(Streamer::getName, s -> s.getPlatforms().stream().map(Platform::getName).toList().toString())
                ));

//    4) Найти стримера с наименишм количествои подписчиков, который работает на заблокированной платформе
        System.out.println(
                streamers.stream().filter(
                                s -> s.getPlatforms().stream().noneMatch(Platform::isRoscomnadzored)
                        ).min(Comparator.comparingDouble(Streamer::getSubscribers))
                        .get()
        );

//    5) Вычислить средний возраст стримеров на платформе twitch
        System.out.println(
                streamers.stream().filter(s -> s.getPlatforms().contains(twitch)).mapToInt(Streamer::getAge).average().getAsDouble()
        );
//    6) Найти любого стримера на youtube с подсчиками > 10k. Если его нет - бросить NoSuchElementException
        System.out.println(
                streamers.stream().filter(
                        s -> s.getSubscribers() > 10000 && s.getPlatforms().contains(youTube)
                ).findAny().orElseThrow(NoSuchFieldException::new)
        );
//    7) *Получить из листа стримеров лист платформ (не должны повторяться платформы в выодном листе)
        System.out.println(
                streamers.stream().flatMap(s -> s.getPlatforms().stream()).distinct().toList()
        );
    }

    static Platform twitch = new Platform("Twitch", false);
    static Platform utv = new Platform("UTV", true);
    static Platform youTube = new Platform("YouTube", false);

    static Streamer xQc = new Streamer("xQc", 5.0,
            24, 100, List.of(twitch, utv, youTube));
    static Streamer mira = new Streamer("mira", 1.0,
            36, 1000, List.of(twitch));
    static Streamer lenaGol0vach = new Streamer("lenaGol0vach", 0.0,
            50, 11235, List.of(twitch, utv, youTube));
    static Streamer rhinoSpiritX = new Streamer("RhinoSpiritX", 4.0,
            35, 14575, List.of(utv));
    static Streamer amouranth = new Streamer("amouranth", 4.99,
            20, 15764, List.of(youTube));
    static Streamer itPedia = new Streamer("ItPedia", 3.45,
            78, 453415, List.of(utv, youTube));
    static Streamer metalyst = new Streamer("Metalyst", 2.78,
            12, 35454, List.of(twitch, youTube));
    static Streamer deferPanic = new Streamer("defer panic", 4.78,
            25, 2434, List.of(youTube));
    static Streamer evrone = new Streamer("Evrone", 4.12,
            32, 1239, List.of(utv));
    static Streamer lofiGirl = new Streamer("Lofi Girl", 4.84,
            73, 91845, List.of(twitch, utv));
}
