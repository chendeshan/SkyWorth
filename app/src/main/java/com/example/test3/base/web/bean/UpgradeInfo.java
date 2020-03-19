package com.example.test3.base.web.bean;

import java.util.List;

public class UpgradeInfo {


    /**
     * camera_fw : {"msg":"success","fw":[{"sid":6,"fwVersion":"cm400pro-008-01-2020031609","fwInfo":{"sid":6,"md5":"f9fb09db6087737d2b16ce82ab8c4039","url":"http://172.22.201.90/group1/M00/00/02/rBbJWl5vKbeAdcRHAjlYWnO5ovE2788.gz","size":37312602},"lVersion":"008","sVersion":"01","tvModel":"","tvVersion":"","aiProvider":"","aiVersion":"","kernelVersion":"","protocalVersion":"","upInfo":"测试升级"}],"total":1}
     * motor_fw : {"msg":"success","fw":[],"total":0}
     * screen_fw : {"msg":"success","fw":[],"total":0}
     */

    private CameraFwBean camera_fw;
    private MotorFwBean motor_fw;
    private ScreenFwBean screen_fw;

    public CameraFwBean getCamera_fw() {
        return camera_fw;
    }

    public void setCamera_fw(CameraFwBean camera_fw) {
        this.camera_fw = camera_fw;
    }

    public MotorFwBean getMotor_fw() {
        return motor_fw;
    }

    public void setMotor_fw(MotorFwBean motor_fw) {
        this.motor_fw = motor_fw;
    }

    public ScreenFwBean getScreen_fw() {
        return screen_fw;
    }

    public void setScreen_fw(ScreenFwBean screen_fw) {
        this.screen_fw = screen_fw;
    }

    public static class CameraFwBean {
        /**
         * msg : success
         * fw : [{"sid":6,"fwVersion":"cm400pro-008-01-2020031609","fwInfo":{"sid":6,"md5":"f9fb09db6087737d2b16ce82ab8c4039","url":"http://172.22.201.90/group1/M00/00/02/rBbJWl5vKbeAdcRHAjlYWnO5ovE2788.gz","size":37312602},"lVersion":"008","sVersion":"01","tvModel":"","tvVersion":"","aiProvider":"","aiVersion":"","kernelVersion":"","protocalVersion":"","upInfo":"测试升级"}]
         * total : 1
         */

        private String msg;
        private int total;
        private List<FwBean> fw;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<FwBean> getFw() {
            return fw;
        }

        public void setFw(List<FwBean> fw) {
            this.fw = fw;
        }

        public static class FwBean {
            /**
             * sid : 6
             * fwVersion : cm400pro-008-01-2020031609
             * fwInfo : {"sid":6,"md5":"f9fb09db6087737d2b16ce82ab8c4039","url":"http://172.22.201.90/group1/M00/00/02/rBbJWl5vKbeAdcRHAjlYWnO5ovE2788.gz","size":37312602}
             * lVersion : 008
             * sVersion : 01
             * tvModel :
             * tvVersion :
             * aiProvider :
             * aiVersion :
             * kernelVersion :
             * protocalVersion :
             * upInfo : 测试升级
             */

            private int sid;
            private String fwVersion;
            private FwInfoBean fwInfo;
            private String lVersion;
            private String sVersion;
            private String tvModel;
            private String tvVersion;
            private String aiProvider;
            private String aiVersion;
            private String kernelVersion;
            private String protocalVersion;
            private String upInfo;

            public int getSid() {
                return sid;
            }

            public void setSid(int sid) {
                this.sid = sid;
            }

            public String getFwVersion() {
                return fwVersion;
            }

            public void setFwVersion(String fwVersion) {
                this.fwVersion = fwVersion;
            }

            public FwInfoBean getFwInfo() {
                return fwInfo;
            }

            public void setFwInfo(FwInfoBean fwInfo) {
                this.fwInfo = fwInfo;
            }

            public String getLVersion() {
                return lVersion;
            }

            public void setLVersion(String lVersion) {
                this.lVersion = lVersion;
            }

            public String getSVersion() {
                return sVersion;
            }

            public void setSVersion(String sVersion) {
                this.sVersion = sVersion;
            }

            public String getTvModel() {
                return tvModel;
            }

            public void setTvModel(String tvModel) {
                this.tvModel = tvModel;
            }

            public String getTvVersion() {
                return tvVersion;
            }

            public void setTvVersion(String tvVersion) {
                this.tvVersion = tvVersion;
            }

            public String getAiProvider() {
                return aiProvider;
            }

            public void setAiProvider(String aiProvider) {
                this.aiProvider = aiProvider;
            }

            public String getAiVersion() {
                return aiVersion;
            }

            public void setAiVersion(String aiVersion) {
                this.aiVersion = aiVersion;
            }

            public String getKernelVersion() {
                return kernelVersion;
            }

            public void setKernelVersion(String kernelVersion) {
                this.kernelVersion = kernelVersion;
            }

            public String getProtocalVersion() {
                return protocalVersion;
            }

            public void setProtocalVersion(String protocalVersion) {
                this.protocalVersion = protocalVersion;
            }

            public String getUpInfo() {
                return upInfo;
            }

            public void setUpInfo(String upInfo) {
                this.upInfo = upInfo;
            }

            public static class FwInfoBean {
                /**
                 * sid : 6
                 * md5 : f9fb09db6087737d2b16ce82ab8c4039
                 * url : http://172.22.201.90/group1/M00/00/02/rBbJWl5vKbeAdcRHAjlYWnO5ovE2788.gz
                 * size : 37312602
                 */

                private int sid;
                private String md5;
                private String url;
                private int size;

                public int getSid() {
                    return sid;
                }

                public void setSid(int sid) {
                    this.sid = sid;
                }

                public String getMd5() {
                    return md5;
                }

                public void setMd5(String md5) {
                    this.md5 = md5;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }
            }
        }
    }

    public static class MotorFwBean {
        /**
         * msg : success
         * fw : []
         * total : 0
         */

        private String msg;
        private int total;
        private List<?> fw;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<?> getFw() {
            return fw;
        }

        public void setFw(List<?> fw) {
            this.fw = fw;
        }
    }

    public static class ScreenFwBean {
        /**
         * msg : success
         * fw : []
         * total : 0
         */

        private String msg;
        private int total;
        private List<?> fw;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<?> getFw() {
            return fw;
        }

        public void setFw(List<?> fw) {
            this.fw = fw;
        }
    }
}
