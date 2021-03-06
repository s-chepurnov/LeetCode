package com.amazon;

import java.util.*;
import java.util.stream.Collectors;

public class TopNBuzzwords {

    public static void main(String[] args) {

        int numToys = 6;
        int topToys = 2;
        int numQuotes = 6;

//        int numToys = 3;
//        int topToys = 2;
//        int numQuotes = 3;

        String[] toys = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};
        //String[] toys = {"support","realize"};
        //String[] toys = {"anacell", "betacellular", "cetracellular", "deltacellular", "eurocellular"};
        //String[] toys = {"region","clearly","follow","business","describe","authority","everyone","power","home","education"};
        //String[] toys = {"interview","evidence","n't","pressure","beat","poor","alone","ok","knowledge","near"};
        //String[] toys = {"four","shake","agreement"};
        //String[] toys = {"indeed","dinner","author"};

        String[] quotes = {
                "Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
                "The new Elmo dolls are super high quality",
                "Expect the Elsa dolls to be very popular this year, Elsa!",
                "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
                "For parents of older kids, look into buying them a drone",
                "Warcraft is slowly rising in popularity ahead of the holiday season"
        };

//        String[] quotes = {
//                "",
//                "realize ",
//                "support "
//        };


//        String[] quotes ={
//                "I love anacell Best services provided by anacell in the town",
//                "betacellular has great services",
//                "deltacellular provides much better services than betacellular",
//                "cetracular is worse than eurocell",
//                "betacellular is better than deltacellular"
//        };

        //power, region
//        String[] quotes = {
//                "school people clearly religious agreement? church scene bit every technology back! entire against everybody civil region never? industry business describe laugh suffer? everyone. television push service energy report clearly president everyone. home",
//                "region describe left region power seek describe authority interest often. everyone general ",
//                "business must clearly period, price? describe several goal decade power home leader sit talk charge standard movie reality business foreign especially. away I program become anything interview class business shake` power else space particularly home partner method parent? study come describe history same? film, against pretty. ",
//                "garden last kind behind may animal mind point step oil any` authority education deep? country none. scientist space imagine moment ten sound before deal industry since ahead finally hit problem run` identify. college member remember ",
//                "color sex baby house five hear result` really any situation talk himself back somebody special media first report style spring board after likely seat agency point purpose apply financial because seven because` Mr` Republican ago huge look policy church from history stand ",
//                "follow recently product business clearly road him? next everyone cancer lay` member on sure, movement come everyone! method region air, church see quite` shoulder down parent beat quickly authority especially likely follow form seem discussion present ",
//                "final cost cultural floor legal Mr! professor? home amount skill follow ",
//                "laugh business lie region morning key carry rest inside indicate oil over pattern power region before color summer maintain power region possible method baby, describe century education option peace chair few item stock hear cell message picture anyone follow language authority agreement movement stage foot ",
//                "follow heat soldier. enough sexual soldier perform future must degree matter affect fine legal no contain, leg able! off information born article record join role American clearly generation material among new structure power during stuff financial power plan avoid speak figure notice party international? cover crime growth take power program our never cost team ",
//                "education power show child particular! else! television management ground standard cause receive suddenly smile scene along consumer number make perform follow serve power home common, enough hundred employee character region option policy pretty professional power tend region` image? step fall different law huge state history like agency home! exist pass everyone legal "
//        };

//        String[] quotes = {
//                "population poor movie claim seek fast peace list finally single. agent beautiful, concern pretty beat couple economic happy, either agency defense, kind fact put. interview social tax phone fight` quite church business character job card develop over over company ",
//                "score evidence! could see interview budget at positive alone past Congress alone green red beyond education! because different. still near note nor drug PM adult interview n't specific ",
//                "call skin start ok interview standard series although` former evidence beat interview important alone see hot area important` kill near political mouth knowledge necessary n't mind reality attorney rock it ",
//                "discussion near people age choose nature make exactly appear. along authority ok choose hard decade beat arm record responsibility. act nice something interview rate? film bar morning around huge style n't, hang goal, final although interesting painting future? director gas improve job ",
//                "evidence civil! event couple born bed beat church during job around activity protect make impact role edge life board people event pressure officer again color sort black near begin north as relate nothing everybody law born message control particularly bit enjoy` factor coach pressure analysis alone child material hundred military from real evidence. factor ",
//                "art leader page free serious catch even among generation daughter father record education impact ",
//                "fund his effect person pressure team` history receive knowledge feeling bill painting himself ahead school` effort hundred campaign die tell, n't left continue seem including` game feeling offer successful near interview leg? important poor group past evening? ",
//                "over painting natural cancer cut program place scientist pressure site cultural interview summer indeed ok both exist rate actually central near chance pressure above evidence change evidence nation evidence back ",
//                "star stock house poor knowledge interview ever child camera item knowledge knowledge level show near evidence interview ",
//                "suggest` behavior billion practice! decide ability real none minute listen manage and court live off occur ready break it five option apply by it player culture! shoot choose including physical say cause major pain employee Republican concern month also quality of main nothing listen ball make enough keep, ",
//                "born small morning have before order other growth? avoid size article story meeting pressure both close behind. recognize perhaps organization kitchen former risk sister anything citizen actually! able leg experience information hundred hear group ",
//                "live interview goal how event poor poor adult sound important involve task boy near technology, poor deal but pressure ",
//                "age interview near often baby ok sign decide eat alone spend near natural, along good interview pressure many interview, affect ok ",
//                "stay arm` evidence sometimes hope central right evening knowledge` police n't first interview mention next knowledge key kind near, particular check season recent include poor dark charge billion evidence something however he join ",
//                "pressure near beat agreement poor effort beat help lot itself floor, media spend ok state also n't data near operation age he pay disease reach ability room like sing rise ",
//                "program attack. require shoulder education deep? least anything system same remove adult never? big suddenly fund knowledge read figure interview rest although development death brother expert fund must manager beat glass alone nor staff accept tell form car have cancer floor sure man. away television collection near religious beat along ",
//                "many financial far ",
//                "poor best! out still knowledge his player enter ago ",
//                "ok n't reflect skill run address enjoy` realize nation majority ",
//                "focus plan let` fact should line` city teacher his near size ok send knowledge? system soldier let conference possible record shake life cell rate hair institution love for "
//        };
//        String[] quotes = {
//                "about buy general enter poor daughter ",
//                "four four? ",
//                "consumer stay "};

//        String[] quotes = {
//                "manage? let especially material get level federal. many born, ",
//                "hot group church special report` suddenly house mind television position computer ",
//                "board any indeed "
//        };


        SolutionBuzzwords solution = new SolutionBuzzwords();
        List<String> result = solution.popularNfeatures(numToys, topToys, toys, numQuotes, quotes);
        //List<String> result = solution.getTopToys(numToys, topToys, toys, numQuotes, quotes);

        System.out.println("result: " + Arrays.toString(result.toArray()));

    }
}

class SolutionBuzzwords {


//    public List<String> getTopToys(int numCompetitors, int topNCompetitors, String[] competitors, int numReviews, String[] reviews) {
//
//        int numFeatures = numCompetitors;
//        int topFeatures = topNCompetitors;
//        String[] possibleFeatures = competitors;
//        int numFeatureRequests = numReviews;
//        String[] featureRequests = reviews;

    public List<String> popularNfeatures(int numFeatures, int topFeatures, String[] possibleFeatures, int numFeatureRequests, String[] featureRequests) {
        String ALPHABET = "abcdefghijklmnopqrstuvwxyz'";

        //possibleFeatures.stream() in case of List object in input
        List<String> possibleFeaturesList = Arrays.asList(possibleFeatures).stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());

        //featureRequests.stream() in case of List object in input
        List<String> featureRequestsList = Arrays.asList(featureRequests).stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());


        //Multiple occurrence of a feature in a quote should be considered as a single mentioned
        Map<String, Integer> occurrencesByQuote = new HashMap<>();
        for(String quote : featureRequestsList) {

            char[] arr = quote.toCharArray();
            for (int i = 0; i < arr.length; ++i) {

                if(ALPHABET.indexOf(arr[i]) >= 0) {
                    continue;
                } else {
                    arr[i] = ' ';
                }
            }
            String quoteAfterReplaces = new String(arr);
            quoteAfterReplaces = quoteAfterReplaces.replace("\\s+"," ");

            //how many occurrences of a feature in all quotes
            String[] words = quoteAfterReplaces.split(" ");
            List<String> local = new ArrayList<>();
            for(String word : words) {

                if( !local.contains(word)) {
                    if (occurrencesByQuote.containsKey(word)) {
                        Integer counter = occurrencesByQuote.get(word);
                        occurrencesByQuote.put(word, ++counter);
                    } else {
                        occurrencesByQuote.put(word, 1);
                    }
                }

                local.add(word);
            }

        }

        // HashMap -> List<Word>
        List<Word> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : occurrencesByQuote.entrySet()) {
            Word word = new Word(entry.getKey(), 0, entry.getValue());
            result.add(word);
        }

        List<Word> topPossibleFeaturesList = result.stream()
            .filter(i -> possibleFeaturesList.contains(i.word))
            .collect(Collectors.toList());

        // If the value of topNFeatures is more than the number of possibleFeatures, return the names of only the toys mentioned in the quotes.
        if(topPossibleFeaturesList.size() < topFeatures) {
            topPossibleFeaturesList = new ArrayList<>(result);
        }

        //process dictionary
        List<String> limitedResult = topPossibleFeaturesList.stream()
                .filter(i -> !i.word.equals(""))
                .sorted((o1, o2) -> {
                    if(o1.localCount > o2.localCount) {
                        return -1;
                    } else if (o1.localCount < o2.localCount) {
                        return 1;
                    } else {
                        return o1.word.compareTo(o2.word);
                    }
                })
                .map(i -> {return i.word;})
                //.map(String::toLowerCase)
                .limit(topFeatures)
                .collect(Collectors.toList());

        return new ArrayList<>(limitedResult);
    }
}

class Word {
    String word;
    Integer totalCount; //count all occurrences
    Integer localCount; //count one in a line (quote)

    public Word(String word, Integer totalCount, Integer localCount) {
        this.word = word;
        this.totalCount = totalCount;
        this.localCount = localCount;
    }
}


//this code was consider total and local occurrences

/*
    public List<String> solve(int numCompetitors, int topNCompetitors, String[] competitors, int numReviews, String[] reviews) {

        int numToys = numCompetitors;
        int topToys = topNCompetitors;
        String[] toys = competitors;
        int numQuotes = numReviews;
        String[] quotes = reviews;

        String ALPHABET = "abcdefghijklmnopqrstuvwxyz'";

        List<String> toysList = Arrays.asList(toys).stream()
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .collect(Collectors.toList());

        List<String> quotesList = Arrays.asList(quotes).stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());


        Map<String, Integer> totalCount = new HashMap<String, Integer>();
        Map<String, Integer> localCount = new HashMap<String, Integer>();

        List<Word> result = new ArrayList<>();


        for(String quote : quotesList) {

            char[] arr = quote.toCharArray();
            for (int i = 0; i < arr.length; ++i) {

                if(ALPHABET.indexOf(arr[i]) >= 0) {
                    continue;
                } else {
                    arr[i] = ' ';
                }
            }

            String quoteAfterReplaces = new String(arr);
            quoteAfterReplaces = quoteAfterReplaces.replace("\\s+"," ");
            String[] words = quoteAfterReplaces.split(" ");

            //total count
            for(String word : words) {
                if (totalCount.containsKey(word)) {
                    Integer counter = totalCount.get(word);
                    totalCount.put(word, ++counter);
                } else {
                    totalCount.put(word, 1);
                }
            }


            //localCount
            List<String> local = new ArrayList<>();
            for(String word : words) {

                if( !local.contains(word)) {
                    if (localCount.containsKey(word)) {
                        Integer counter = localCount.get(word);
                        localCount.put(word, ++counter);
                    } else {
                        localCount.put(word, 1);
                    }
                }

                local.add(word);
            }

        }

        // (totalMap, localMap) -> List<Word>
        for (Map.Entry<String, Integer> entry : totalCount.entrySet()) {
            Integer lclCnt = localCount.get(entry.getKey());
            Word word = new Word(entry.getKey(), entry.getValue(), lclCnt);
            result.add(word);
        }

        List<Word> debug = result.stream()
                .filter(i -> toysList.contains(i.word))
                .collect(Collectors.toList());

        // If the value of topToys is more than the number of toys, return the names of only the toys mentioned in the quotes.
        if(debug.size() < topNCompetitors) {
            debug = new ArrayList<>(result);
        }

        //process dictionary
        List<String> limitedResult = debug.stream()
                .filter(i -> !i.word.equals(""))
                .sorted((o1, o2) -> {
                    if(o1.totalCount > o2.totalCount) {
                        return -1;
                    } else if (o1.totalCount < o2.totalCount) {
                        return 1;
                    } else {

                        if(o1.localCount > o2.localCount) {
                            return -1;
                        } else if (o1.localCount < o2.localCount) {
                            return 1;
                        } else {
                            return o1.word.compareTo(o2.word);
                        }
                    }
                })
                .map(i -> {return i.word;})
                //.map(String::toLowerCase)
                .limit(topToys)
                .collect(Collectors.toList());

        return new ArrayList<>(limitedResult);
    }
    */