##AudioManager
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
    
  
