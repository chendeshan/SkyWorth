package com.example.test3.base.web.bean;

import java.util.List;

public class UpgradeInfoBean extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * camera_fw : {"msg":"success","fw":[{"sid":7,"fwVersion":"cm400pro-010-01-2020031616","fwInfo":{"sid":7,"md5":"ef9aeea77ec79c8b245659ed3e0f97bc","url":"http://172.22.201.90/group1/M00/00/02/rBbJWl50KH2AGTBUAjleHVw29CI4536.gz","size":37314077},"lVersion":"010","sVersion":"01","tvModel":"","tvVersion":"","aiProvider":"","aiVersion":"","kernelVersion":"","protocalVersion":"","upInfo":"测试2"},{"sid":8,"fwVersion":"cm400pro-010-01-2020031616","fwInfo":{"sid":8,"md5":"ef9aeea77ec79c8b245659ed3e0f97bc","url":"http://172.22.201.90/group1/M00/00/02/rBbJWl50LmWAIl8GAjleHVw29CI1471.gz","size":37314077},"lVersion":"010","sVersion":"02","tvModel":"","tvVersion":"","aiProvider":"","aiVersion":"","kernelVersion":"","protocalVersion":"","upInfo":"测试小版本"}],"total":3}
         * motor_fw : {"msg":"success","fw":[],"total":0}
         * screen_fw : {"msg":"success","fw":[],"total":0}
         */

        private DataBean.CameraFwBean camera_fw;
        private DataBean.MotorFwBean motor_fw;
        private DataBean.ScreenFwBean screen_fw;

        public DataBean.CameraFwBean getCamera_fw() {
            return camera_fw;
        }

        public void setCamera_fw(DataBean.CameraFwBean camera_fw) {
            this.camera_fw = camera_fw;
        }

        public DataBean.MotorFwBean getMotor_fw() {
            return motor_fw;
        }

        public void setMotor_fw(DataBean.MotorFwBean motor_fw) {
            this.motor_fw = motor_fw;
        }

        public DataBean.ScreenFwBean getScreen_fw() {
            return screen_fw;
        }

        public void setScreen_fw(DataBean.ScreenFwBean screen_fw) {
            this.screen_fw = screen_fw;
        }

        public static class CameraFwBean {
            /**
             * msg : success
             * fw : [{"sid":7,"fwVersion":"cm400pro-010-01-2020031616","fwInfo":{"sid":7,"md5":"ef9aeea77ec79c8b245659ed3e0f97bc","url":"http://172.22.201.90/group1/M00/00/02/rBbJWl50KH2AGTBUAjleHVw29CI4536.gz","size":37314077},"lVersion":"010","sVersion":"01","tvModel":"","tvVersion":"","aiProvider":"","aiVersion":"","kernelVersion":"","protocalVersion":"","upInfo":"测试2"},{"sid":8,"fwVersion":"cm400pro-010-01-2020031616","fwInfo":{"sid":8,"md5":"ef9aeea77ec79c8b245659ed3e0f97bc","url":"http://172.22.201.90/group1/M00/00/02/rBbJWl50LmWAIl8GAjleHVw29CI1471.gz","size":37314077},"lVersion":"010","sVersion":"02","tvModel":"","tvVersion":"","aiProvider":"","aiVersion":"","kernelVersion":"","protocalVersion":"","upInfo":"测试小版本"}]
             * total : 3
             */

            private String msg;
            private int total;
            private List<DataBean.CameraFwBean.FwBean> fw;

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

            public List<DataBean.CameraFwBean.FwBean> getFw() {
                return fw;
            }

            public void setFw(List<DataBean.CameraFwBean.FwBean> fw) {
                this.fw = fw;
            }

            public static class FwBean {
                /**
                 * sid : 7
                 * fwVersion : cm400pro-010-01-2020031616
                 * fwInfo : {"sid":7,"md5":"ef9aeea77ec79c8b245659ed3e0f97bc","url":"http://172.22.201.90/group1/M00/00/02/rBbJWl50KH2AGTBUAjleHVw29CI4536.gz","size":37314077}
                 * lVersion : 010
                 * sVersion : 01
                 * tvModel :
                 * tvVersion :
                 * aiProvider :
                 * aiVersion :
                 * kernelVersion :
                 * protocalVersion :
                 * upInfo : 测试2
                 */

                private int sid;
                private String fwVersion;
                private DataBean.CameraFwBean.FwBean.FwInfoBean fwInfo;
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

                public DataBean.CameraFwBean.FwBean.FwInfoBean getFwInfo() {
                    return fwInfo;
                }

                public void setFwInfo(DataBean.CameraFwBean.FwBean.FwInfoBean fwInfo) {
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
                     * sid : 7
                     * md5 : ef9aeea77ec79c8b245659ed3e0f97bc
                     * url : http://172.22.201.90/group1/M00/00/02/rBbJWl50KH2AGTBUAjleHVw29CI4536.gz
                     * size : 37314077
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
}
