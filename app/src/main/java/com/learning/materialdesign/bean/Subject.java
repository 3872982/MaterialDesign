package com.learning.materialdesign.bean;

public class Subject {
        /**
         * director : 弗兰克·德拉邦特 Frank Darabont
         * img : https://img2.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg
         * m_id : 1292052
         * order_num : 0
         * quote : 希望让人自由。
         * score : 9.7
         * title : 肖申克的救赎
         */

        public String director;
        public String img;
        public String m_id;
        public int order_num;
        public String quote;
        public String score;
        public String title;

        @Override
        public String toString() {
                return "Subject{" +
                        "director='" + director + '\'' +
                        ", img='" + img + '\'' +
                        ", m_id='" + m_id + '\'' +
                        ", order_num=" + order_num +
                        ", quote='" + quote + '\'' +
                        ", score='" + score + '\'' +
                        ", title='" + title + '\'' +
                        '}';
        }
}
