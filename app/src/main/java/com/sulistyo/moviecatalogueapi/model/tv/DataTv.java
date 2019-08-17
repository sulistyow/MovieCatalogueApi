package com.sulistyo.moviecatalogueapi.model.tv;

import java.util.ArrayList;

public class DataTv {
    public static String[][] data = new String[][]{
            {"The Flash",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/fki3kBlwJzFp8QohL43g9ReV455.jpg"},
            {"Arrow",
                    "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg"},
            {"Marvel's Agents of S.H.I.E.L.D.",
                    "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/cXiETfFK1BTLest5fhTLfDLRdL6.jpg"},
            {"Game of Thrones",
                    "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg"},
            {"Gotham",
                    "Before there was Batman, there was GOTHAM. Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg"},
            {"Supernatural",
                    "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/3iFm6Kz7iYoFaEcj4fLyZHAmTQA.jpg"},
            {"Stranger Things",
                    "When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces, and one strange little girl.",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/x2LSRK2Cm7MZhjluni1msVJ3wDF.jpg"},
            {"Dragon Ball Z",
                    "Dragon Ball Z is a Japanese animated television series produced by Toei Animation. Dragon Ball Z is the sequel to the Dragon Ball anime and adapts the last 26 volumes of the original 42 volume Dragon Ball manga series created by Akira Toriyama The series Debut in 1988-1995 on Weekly Shounen Jump. Dragon Ball Z depicts the continuing adventures of Goku and his companions to defend against an assortment of villains which seek to destroy or rule the Earth. The anime first aired in Japan from April 25, 1989, to January 31, 1996, and was dubbed in several territories around the world, including the United States, Europe, and in Latin America.",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/dBsDWUcdfbuZwglgyeeQ9ChRoS4.jpg"},
            {"One-Punch Man",
                    "Saitama is a hero who only became a hero for fun. After three years of “special” training, though, he’s become so strong that he’s practically invincible. In fact, he’s too strong—even his mightiest opponents are taken out with a single punch, and it turns out that being devastatingly powerful is actually kind of a bore. With his passion for being a hero lost along with his hair, yet still faced with new enemies every day, how much longer can he keep it going?",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/iE3s0lG5QVdEHOEZnoAxjmMtvne.jpg"},
            {"Attack on Titan",
                    "Several hundred years ago, humans were nearly exterminated by Titans. Titans are typically several stories tall, seem to have no intelligence, devour human beings and, worst of all, seem to do it for the pleasure rather than as a food source. A small percentage of humanity survived by walling themselves in a city protected by extremely high walls, even taller than the biggest Titans. Flash forward to the present and the city has not seen a Titan in over 100 years. Teenage boy Eren and his foster sister Mikasa witness something horrific as the city walls are destroyed by a Colossal Titan that appears out of thin air. As the smaller Titans flood the city, the two kids watch in horror as their mother is eaten alive. Eren vows that he will murder every single Titan and take revenge for all of mankind.",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/fBX1QMl4iZOKxuRuBOMnoHlmS4l.jpg"}
    };

    public static ArrayList<TV> getTv() {
        ArrayList<TV> tv = new ArrayList<>();
        for (String[] mData : data) {
            TV tvs = new TV();
            tvs.setTitle(mData[0]);
            tvs.setDesc(mData[1]);
            tvs.setPhoto(mData[2]);
            tv.add(tvs);
        }
        return tv;
    }

}
