*USE `vithusaynini`;
create table `useruser`(
   `id` int NOT NULL AUTO_INCREMENT,
   `username` varchar(255) NOT NULL,
   isPremium boolean,
   `name` varchar(255) NOT NULL,
   primary key (id)
);*/
/*
ALTER TABLE `user`
ADD `profileImageUrl` varchar(1000) NOT NULL;

ALTER TABLE `user`
DROP `profileImageUrl` varchar(1000) NOT NULL;
*/
create table `tv_series`(
   `id` int NOT NULL AUTO_INCREMENT,
   `main_banner` varchar(255) NOT NULL,
   `video_link` varchar(255) NOT NULL,
   `title`  varchar(255),
    `category`  varchar(255),
   `description` varchar(1000) NOT NULL,
   primary key (id)
);

INSERT INTO vithusaynini.tv_series(`main_banner`,`video_link`,`title`,`category`,`description`) VALUES
('https://sund-images.sunnxt.com/173707/500x750_Sardar_173707_12c2fa20-34e2-49ed-bcf4-9533e5d5a28e.jpg',
'https://www.youtube.com/watch?v=jmWHFc8Qwy4','Sardar','MOVIES','Bank employee Sathyas simple life turns into a nightmare, when he gives refuge to a distressed stranger Thara who, sneaks into his car. Later'),
('https://sund-images.sunnxt.com/171212/500x750_Maaveeran_171212_a4a719b9-c988-4052-b26f-db5d5be20184.jpg',
'https://www.youtube.com/watch?v=jmWHFc8Qwy4','Maaveran','MOVIES','A meek comic book artist Sathya overlooks even the systems injustice. After an accidental fall, he starts hearing a voice that controls his life to transform him into a warrior for the downtrodden. Will Sathya fight for the greater good?');
;
