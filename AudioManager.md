#AudioManager
    AudioManager am = mContext.getSystemService(Context.AUDIO_SERVICE);
    AudioManager提供下列方法用来查询当前Audio输出的状态：  
    isBluetoothA2dpOn()：检查A2DPAudio是否通过蓝牙耳机；  
    isSpeakerphoneOn()：检查扬声器是否打开；  
    isWiredHeadsetOn()：检查线控耳机是否连着；注意这个方法只是用来判断耳机是否是插入状态，并不能用它的结果来判定当前的Audio是通过耳机输出的，这还依赖于其他条件。  
    
      方法:  
    void Android.media.AudioManager.adjustStreamVolume(int streamType, int direction, int flags)：第二个表示调整音乐的大小，第三个参数表示显示调整是的标志 AudioManager.FLAG_SHOW_UI；调整手机类型的声音；第一个参数的几个值
    STREAM_ALARM：手机闹铃的声音
    STREAM_MUSIC：手机音乐的声音
    STREAM_DTMF：DTMF音调的声音
    STREAM_RING：电话铃声的声音
    STREAM_NOTFICATION：系统提示的声音
    STREAM_SYSTEM：系统的声音
    STREAM_VOICE_CALL：语音电话声音  
    第二个direction,是调整的方向,增加或减少,可以是:    
    ADJUST_LOWER 降低音量    
    ADJUST_RAISE 升高音量    
    ADJUST_SAME 保持不变,这个主要用于向用户展示当前的音量    
    第三个flags是一些附加参数,只介绍两个常用的
    FLAG_SHOW_UI 调整时显示音量条,就是按音量键出现的那个  
    FLAG_PLAY_SOUND 调整音量时播放声音    
    
    public void setMode (int mode) 设置音频模式。
    音频模式包含音频路由和电话层的行为。因此，这个方法只能用于代替音频设置的平台范围管理应用程序或主要电话应用程序。特别地，MODE_IN_CALL模式只能用在当打电话时的电话程序中，因为它会引起信号从音频层馈入到平台混音器。
    参数mode  请求的音频模式(MODE_NORMAL, MODE_RINGTONE, MODE_IN_CALL 或MODE_IN_COMMUNICATION)。通知HAL当前的音频状态以便它能适当的路由音频。
    
    public void setStreamVolume (int streamType, int index, int flags)  设置特定流的音量索引。
    最大音量  int  maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    当前音量  int  currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
    
    ACTION_AUDIO_BECOMING_NOISY    
    Broadcast intent, a hint for applications that audio is about to become 'noisy' due to a change in audio outputs.
    
    private class NoisyAudioStreamReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction())) {
            // Pause the playback
          }
        }
    }
    private IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
    
##Audio Focus
     AudioFocus机制管理对Audio资源的竞争的管理与协调。Android是多任务系统，Audio系统是竞争资源。
     使用前，用requestAudioFocus()申请AudioFocus，并根据应用的实际选取恰当的durationHint值；
     正确的在AudioManager.OnAudioFocusChangeListener中响应AudioFocus失去和重新获取事件；
     Audio使用结束，用abandonAudioFocus()归还AudioFocus。
  
    方法:public int requestAudioFocus (AudioManager.OnAudioFocusChangeListener l, int streamType, int durationHint)
    
     AudioManager.OnAudioFocusChangeListener是申请成功之后监听AudioFocus使用情况的Listener，后续如果有别的程序要竞争AudioFocus，都是通过这个Listener的onAudioFocusChange()方法来通知这个Audio Focus的使用者的。
     streamType是《Android中的Audio播放：音量和远程播放控制》中说明的AudioStream，其值取决于AudioManager中的STREAM_xxx，在AudioStream的裁决机制中并未有什么实际意义；
     durationHint是持续性的指示：
     AUDIOFOCUS_GAIN指示申请得到的Audio Focus不知道会持续多久，一般是长期占有；
     AUDIOFOCUS_GAIN_TRANSIENT指示要申请的AudioFocus是暂时性的，会很快用完释放的；
     AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK不但说要申请的AudioFocus是暂时性的，还指示当前正在使用AudioFocus的可以继续播放，只是要“duck”一下（降低音量）。
     
     Returns
     AUDIOFOCUS_REQUEST_FAILED or AUDIOFOCUS_REQUEST_GRANTED
     
    OnAudioFocusChangeListener afChangeListener = new OnAudioFocusChangeListener() {
    public void onAudioFocusChange(int focusChange) {
        if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT
            // Pause playback
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            // Resume playback 
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            am.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
            am.abandonAudioFocus(afChangeListener);
            // Stop playback
        }
      }
    };
    
     AUDIOFOCUS_GAIN：获得了Audio Focus；
     AUDIOFOCUS_LOSS：失去了Audio Focus，并将会持续很长的时间。这里因为可能会停掉很长时间，所以不仅仅要停止Audio的播放，最好直接释放掉Media资源。而因为停止播放Audio的时间会很长，如果程序因为这个原因而失去AudioFocus，最好不要让它再次自动获得AudioFocus而继续播放，
     不然突然冒出来的声音会让用户感觉莫名其妙，感受很不好。这里直接放弃AudioFocus，当然也不用再侦听远程播放控制【如下面代码的处理】。要再次播放，除非用户再在界面上点击开始播放，才重新初始化Media，进行播放。
     AUDIOFOCUS_LOSS_TRANSIENT：暂时失去Audio Focus，并会很快再次获得。必须停止Audio的播放，但是因为可能会很快再次获得AudioFocus，这里可以不释放Media资源；
     AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK：暂时失去AudioFocus，但是可以继续播放，不过要在降低音量。

  
  
  
  
  
  
  
  
  
  
  
  
