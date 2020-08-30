package com.wangp.myaop.design_pattern.behavioral.state;

/**
 * <pre>
 * classname CourseVideoContext
 * description
 * </pre>
 *
 * @author wangp
 * @date 2020/8/30 13:20
 **/
public class CourseVideoContext {

    private CourseVideoState courseVideoState;

    public static final CourseVideoState PLAY_VIDEO_STATE = new PlayState();
    public static final CourseVideoState SPEED_VIDEO_STATE = new SpeedState();
    public static final CourseVideoState PAUSE_VIDEO_STATE = new PauseState();
    public static final CourseVideoState STOP_VIDEO_STATE = new StopState();

    public CourseVideoState getCourseVideoState() {
        return courseVideoState;
    }

    public void setCourseVideoState(CourseVideoState courseVideoState) {
        this.courseVideoState = courseVideoState;
        this.courseVideoState.setCourseVideoContext(this);
    }

    public void play() {
        this.courseVideoState.play();
    }

    public void speed() {
        this.courseVideoState.speed();
    }

    public void pause() {
        this.courseVideoState.pause();
    }

    public void stop() {
        this.courseVideoState.stop();
    }
}
