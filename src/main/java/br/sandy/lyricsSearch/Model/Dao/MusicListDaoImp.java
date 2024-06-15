package br.sandy.lyricsSearch.Model.Dao;

import br.sandy.lyricsSearch.Model.Music;
import java.util.ArrayList;
import java.util.List;

public class MusicListDaoImp implements MusicListDao {

    private static MusicListDaoImp instance;
    public List<Music>MusicList;

    MusicListDaoImp(){
        MusicList=new ArrayList<>();
        Music musica1 = new Music("Yellow","Coldplay","Parachutes","Look at the stars\n" +
                "Look how they shine for you\n" +
                "And everything you do\n" +
                "Yeah, they were all yellow\n" +
                "I came along\n" +
                "I wrote a song for you\n" +
                "And all the things you do\n" +
                "And it was called Yellow\n" +
                "So then I took my turn\n" +
                "Oh, what a thing to have done\n" +
                "And it was all yellow\n" +
                "Your skin, oh yeah, your skin and bones\n" +
                "Turn into something beautiful\n" +
                "And you know, you know I love you so\n" +
                "You know I love you so\n" +
                "I swam across\n" +
                "I jumped across for you\n" +
                "Oh, what a thing to do\n" +
                "'Cause you were all yellow\n" +
                "I drew a line\n" +
                "I drew a line for you\n" +
                "Oh, what a thing to do\n" +
                "And it was all yellow\n" +
                "And your skin, oh yeah, your skin and bones\n" +
                "Turn into something beautiful\n" +
                "And you know, for you, I'd bleed myself dry\n" +
                "For you, I'd bleed myself dry\n" +
                "It's true\n" +
                "Look how they shine for you\n" +
                "Look how they shine for you\n" +
                "Look how they shine for\n" +
                "Look how they shine for you\n" +
                "Look how they shine for you\n" +
                "Look how they shine\n" +
                "Look at the stars\n" +
                "Look how they shine for you\n" +
                "And all the things that you do");

        Music musica2 = new Music("Numb","Linkin Park","Meteora","I'm tired of being what you want me to be\n" +
                "Feeling so faithless, lost under the surface\n" +
                "I don't know what you're expecting of me\n" +
                "Put under the pressure of walking in your shoes\n" +
                "Every step that I take is another mistake to you\n" +
                "(Caught in the undertow, just caught in the undertow)\n" +
                "I've become so numb, I can't feel you there\n" +
                "Become so tired, so much more aware\n" +
                "I'm becoming this, all I want to do\n" +
                "Is be more like me and be less like you\n" +
                "Can't you see that you're smothering me?\n" +
                "Holding too tightly, afraid to lose control\n" +
                "'Cause everything that you thought I would be\n" +
                "Has fallen apart right in front of you\n" +
                "Every step that I take is another mistake to you\n" +
                "(Caught in the undertow, just caught in the undertow)\n" +
                "And every second I waste is more than I can take\n" +
                "I've become so numb, I can't feel you there\n" +
                "Become so tired, so much more aware\n" +
                "I'm becoming this, all I want to do\n" +
                "Is be more like me and be less like you\n" +
                "And I know\n" +
                "I may end up failing too\n" +
                "But I know\n" +
                "You were just like me, with someone disappointed in you\n" +
                "I've become so numb, I can't feel you there\n" +
                "Become so tired, so much more aware\n" +
                "I'm becoming this, all I want to do\n" +
                "Is be more like me and be less like you\n" +
                "I've become so numb, I can't feel you there\n" +
                "I'm tired of being what you want me to be\n" +
                "I've become so numb, I can't feel you there\n" +
                "I'm tired of being what you want me to be");

        Music musica3 = new Music("we can´t be friends","Ariana Grande","Eternal Sunshine", "I didn't think you'd understand me\n" +
                "How could you ever even try?\n" +
                "I don't wanna tiptoe, but I don't wanna hide\n" +
                "But I don't wanna feed this monstrous fire\n" +
                "Just wanna let this story die\n" +
                "And I'll be alright\n" +
                "We can't be friends\n" +
                "But I'd like to just pretend\n" +
                "You cling to your papers and pens\n" +
                "Wait until you like me again\n" +
                "Wait for your love\n" +
                "Love, I'll wait for your love\n" +
                "Me and my truth, we sit in silence\n" +
                "Baby girl, it's just me and you\n" +
                "'Cause I don't wanna argue, but I don't wanna bite\n" +
                "My tongue, yeah, I think I'd rather die\n" +
                "You got me misunderstood, but at least I look this good\n" +
                "We can't be friends\n" +
                "But I'd like to just pretend\n" +
                "You cling to your papers and pens\n" +
                "Wait until you like me again\n" +
                "Wait for your love\n" +
                "Love, I'll wait for your love\n" +
                "I'll wait for your love\n" +
                "Love, I'll wait for your love\n" +
                "Know that you made me\n" +
                "I don't like how you paint me, yet I'm still here hanging\n" +
                "Not what you made me\n" +
                "It's something like a daydream\n" +
                "But I feel so seen in the night\n" +
                "So for now, it's only me\n" +
                "And maybe that's all I need\n" +
                "We can't be friends\n" +
                "But I'd like to just pretend\n" +
                "You cling to your papers and pens\n" +
                "Wait until you like me again\n" +
                "Wait for your love\n" +
                "Love, I'll wait for your love\n" +
                "I'll wait for your love\n" +
                "Love, I'll wait for your love\n" +
                "I'll wait for your love\n" +
                "I'll wait for your love\n" +
                "I'll wait for your love\n" +
                "I'll wait for your love\n" +
                "I'll wait for your love");

        MusicList.add(musica1);
        MusicList.add(musica2);
        MusicList.add(musica3);
    }

    @Override
    public List<Music> getAllMusics() {
        return MusicList;
    }

    @Override
    public void addMusics(Music musica) {
        MusicList.add(musica);

    }

    @Override
    public void updateMusic(int index, Music musica) {
        MusicList.set(index, musica);

    }

    @Override
    public Music getMusic(int index) {
        return MusicList.get(index);
    }

    public static synchronized MusicListDaoImp getInstance() {
        if (instance==null) {
            instance = new MusicListDaoImp();
        }

            return instance;

    }

}
