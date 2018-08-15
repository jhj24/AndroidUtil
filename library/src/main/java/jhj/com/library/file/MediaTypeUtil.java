package jhj.com.library.file;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 判断多媒体类型
 * Created by jianhaojie on 2017/6/29.
 */

public class MediaTypeUtil {

    private Class<?> mMediaFile;
    private Method getFileTypeMethod;
    private Method audioFileTypeMethod;
    private Method videoFileTypeMethod;
    private Method imageFileTypeMethod;
    private Field fileType;

    public MediaTypeUtil() {
        initReflect();
    }

    private void initReflect() {
        try {
            mMediaFile = Class.forName("android.media.MediaFile");
            Class<?> mMediaFileType = Class.forName("android.media.MediaFile$MediaFileType");

            fileType = mMediaFileType.getField("fileType");

            String getFileType = "getFileType";
            getFileTypeMethod = mMediaFile.getMethod(getFileType, String.class);

            String isAudioFileType = "isAudioFileType";
            audioFileTypeMethod = mMediaFile.getMethod(isAudioFileType, int.class);

            String isVideoFileType = "isVideoFileType";
            videoFileTypeMethod = mMediaFile.getMethod(isVideoFileType, int.class);

            String isImageFileType = "isImageFileType";
            imageFileTypeMethod = mMediaFile.getMethod(isImageFileType, int.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据path返回媒体文件类型
     *
     * @param path 媒体路径
     * @return 文件类型
     */
    public int getMediaFileType(String path) {

        int type = 0;

        try {
            Object obj = getFileTypeMethod.invoke(mMediaFile, path);
            if (obj == null) {
                type = -1;
            } else {
                type = fileType.getInt(obj);
            }
        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return type;
    }

    /**
     * 音频
     *
     * @param fileType 文件类型
     * @return boolean
     */
    public boolean isAudioFile(int fileType) {
        boolean isAudioFile = false;
        try {
            isAudioFile = (Boolean) audioFileTypeMethod.invoke(mMediaFile, fileType);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return isAudioFile;
    }

    /**
     * 视频
     *
     * @param fileType 文件类型
     * @return boolean
     */
    public boolean isVideoFile(int fileType) {
        boolean isVideoFile = false;
        try {
            isVideoFile = (Boolean) videoFileTypeMethod.invoke(mMediaFile, fileType);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return isVideoFile;
    }

    /**
     * 图片
     *
     * @param fileType 文件类型
     * @return boolean
     */
    public boolean isImageFile(int fileType) {
        boolean isImageFile = false;
        try {
            isImageFile = (Boolean) imageFileTypeMethod.invoke(mMediaFile, fileType);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return isImageFile;
    }

}
