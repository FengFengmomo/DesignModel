package com.sm.struct;

public class Adapter {
    interface Audio{
        void playmp3(String name);
        void playmp4(String name);
    }
    class Mp3 implements Audio{
        @Override
        public void playmp3(String name) {
            System.out.println("mp3 play");
        }

        @Override
        public void playmp4(String name) {

        }
    }
    class Mp4 implements Audio{
        @Override
        public void playmp3(String name) {
            
        }

        @Override
        public void playmp4(String name) {
            System.out.println("Mpe play");
        }
    }
    interface MediaPlayer{
        void play(String type,String name);
    }
    class MediaAdapter implements MediaPlayer{
        Audio audio;
        @Override
        public void play(String type,String name) {
            if ("mp3".equals(type)){
                audio = new Mp3();
                audio.playmp3(name);
            }
            if ("mp4".equals(type)){
                audio = new Mp4();
                audio.playmp4(name);
            }
        }
    }
    class AudioPlayer implements MediaPlayer{
        MediaAdapter mediaAdapter;

        @Override
        public void play(String type,String name) {
            // 播放内置格式
            if ("rmvb".equals(type)){
                System.out.println("play rmvb");
            }else if (type.equals("mp3") || type.equals("mp4")){
                mediaAdapter = new MediaAdapter();
                mediaAdapter.play(type,name);
            }else {
                System.out.println(type+"not support");
            }
        }
    }
}
