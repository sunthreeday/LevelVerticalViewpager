package com.wz.levelverticalviewpager.model;

import java.io.Serializable;
import java.util.List;

public class AllDesignersEntity extends BaseEntity<AllDesignersEntity> implements Serializable{

    private List<DesignerListBean> designer_list;

    public List<DesignerListBean> getDesigner_list() {
        return designer_list;
    }

    public void setDesigner_list(List<DesignerListBean> designer_list) {
        this.designer_list = designer_list;
    }

    public static class DesignerListBean implements Serializable{

        private String descript;
        private String designer_level;
        private String designer_name;
        private String designer_price;
        private String designer_uid;
        private String full_body_shot_url;
        private String head_image_url;
        private String personality_photo_url;
        private String plantform_descript;
        private String self_introduction_video_url;
        private String video_url;

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public String getDesigner_level() {
            return designer_level;
        }

        public void setDesigner_level(String designer_level) {
            this.designer_level = designer_level;
        }

        public String getDesigner_name() {
            return designer_name;
        }

        public void setDesigner_name(String designer_name) {
            this.designer_name = designer_name;
        }

        public String getDesigner_price() {
            return designer_price;
        }

        public void setDesigner_price(String designer_price) {
            this.designer_price = designer_price;
        }

        public String getDesigner_uid() {
            return designer_uid;
        }

        public void setDesigner_uid(String designer_uid) {
            this.designer_uid = designer_uid;
        }

        public String getFull_body_shot_url() {
            return full_body_shot_url;
        }

        public void setFull_body_shot_url(String full_body_shot_url) {
            this.full_body_shot_url = full_body_shot_url;
        }

        public String getHead_image_url() {
            return head_image_url;
        }

        public void setHead_image_url(String head_image_url) {
            this.head_image_url = head_image_url;
        }

        public String getPersonality_photo_url() {
            return personality_photo_url;
        }

        public void setPersonality_photo_url(String personality_photo_url) {
            this.personality_photo_url = personality_photo_url;
        }

        public String getPlantform_descript() {
            return plantform_descript;
        }

        public void setPlantform_descript(String plantform_descript) {
            this.plantform_descript = plantform_descript;
        }

        public String getSelf_introduction_video_url() {
            return self_introduction_video_url;
        }

        public void setSelf_introduction_video_url(String self_introduction_video_url) {
            this.self_introduction_video_url = self_introduction_video_url;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }
    }
}
