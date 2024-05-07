-- Drop tables if they exist
DROP TABLE IF EXISTS Powers;
DROP TABLE IF EXISTS Equipment;
DROP TABLE IF EXISTS Blog;
DROP TABLE IF EXISTS Hero;

-- Create Hero Table
CREATE TABLE Hero (
                      HeroId INT AUTO_INCREMENT PRIMARY KEY,
                      CodeName VARCHAR(255),
                      RealName VARCHAR(255),
                      Bio TEXT,
                      Alignment VARCHAR(255),
                      Images VARCHAR(255),
                      Descriptions TEXT,
                      Personality TEXT,
                      Height Varchar(20),
                      Weight Varchar(20),
                      Emblem Varchar(255)
);

-- Insert Data into Hero table

INSERT INTO Hero (CodeName, RealName, Bio, Alignment, Images, Descriptions, Personality, Height, Weight, Emblem) VALUES
                                                                                                                     ('Windchild', 'Lance Talon', 'Lance Talon, born and raised in Chicago, Illinois, is a teenager grappling with his extraordinary superpowers in a world of mundane challenges. Born to a family with a heroic legacy, Lance struggles to control his abilities while navigating the complexities of adolescence and family dynamics. With the opportunity to attend the prestigious Tetran Academy aboard a space station in a far-off galaxy, Lance embarks on a journey of self-discovery and adventure.', 'Good', 'images/Windchild.jpg', 'Lance Talon stands at an average height, his frame lean and agile. His hair, a vibrant shade of blonde, frames his face in tousled waves, complementing the warm, earthy hues of his brown eyes. In his earlier days at Tetran Academy, Lance could often be found clad in a simple ensemble of a comfortable t-shirt paired with denim jeans. On days when he sought an extra dose of luck or a reminder of his roots, he would don his grandfather''s cherished scarf, its fabric worn but imbued with sentimental value. However, since receiving the mystical artifact known as the Thundercloak, Lance''s appearance has undergone a notable transformation. The Thundercloak, a garment crafted from the ethereal feathers of the legendary Thunderbird Zylost, now envelops him in a cloak of pure white. This cloak has become emblematic of his connection to the revered entity, earning him recognition as her chosen avatar. When donning the Thundercloak, Lance carries with him the formidable Headwind, manifested in the form of a sleek spear, a symbol of his newfound power and responsibility. On occasions when he is not cloaked in the Thundercloak, Lance is often seen with a soft blue scarf wound snugly around his neck. This scarf holds a special significance, as it is crafted from the remnants of his original scarf, merged seamlessly with Headwind to create an effective camouflage for the spear when it is not in use.', 'Lance Talon is a courageous and determined teenager who faces life''s challenges with resilience and a strong sense of purpose. Growing up in Chicago, Illinois, Lance was raised in a family with a proud heroic legacy, instilling in him a deep sense of duty and responsibility to use his extraordinary superpowers for the greater good. Despite his exceptional abilities, Lance grapples with the complexities of adolescence and the weight of his family''s expectations. He struggles to control his powers while navigating the ordinary challenges of teenage life, including friendships, school, and family dynamics. However, Lance''s journey takes a transformative turn when he receives the opportunity to attend the prestigious Tetran Academy aboard a space station in a far-off galaxy. This marks the beginning of a profound journey of self-discovery and adventure, where Lance embraces his true potential and strives to become the hero he was destined to be. In terms of personality, Lance is depicted as determined, compassionate, and introspective. He possesses a strong sense of empathy and cares deeply about those around him, often putting the needs of others before his own. Lance''s courage and resilience are evident in his willingness to confront challenges head-on, even in the face of adversity. Lance''s attire reflects his personality and journey. His choice of clothing, from his simple t-shirt and jeans to the symbolic Thundercloak, highlights his evolving identity and connection to his heroic destiny. The Thundercloak, crafted from the legendary Thunderbird Zylost''s feathers, serves as a powerful symbol of Lance''s newfound power and responsibility, emphasizing his role as the chosen avatar of a revered entity.', '180 cm', '70 kg', NULL),
                                                                                                                     ('Falinex', 'Paul Wyvernel', 'Born to the Mighty GodBeast Falinar and a wealthy heiress to a major intergalactic crime family, Paul Wyvernel has a major share of familial resentment. Unlike his father and mother, Paul only wants to help others, a selfless sentiment that managed to get him in hot water with the rest of his clan. Which is why, when offered the chance to go to Tetran Academy and train to be a hero, far from the judging eyes of the Wyvernels, Paul left home in a heartbeat. Now, his family wants Paul back, desperate to use his sizable abilities for their own criminal ends. A boy on the run, Paul is determined not to give up on his dream of becoming a hero of the people, no matter the cost.', 'Good', 'images/Falinex.jpg', NULL, NULL, '180 cm', '75 kg', NULL),
                                                                                                                     ('Dillo', 'Dillon Arma', 'Half Aeon, half Fenarian shape-changer, Dillon Arma is unique among the others of his kind. While his ability to change into a half-man-half-armadillo form is nothing special, especially among Fenarians, his hidden Aeon powers make him an unstoppable force to be reckoned with. Not only that, but his obsession with Mexican culture and mannerisms have made him the subject of ridicule on his home planet. While his powers may pale compared to his fellow students at Tetran Academy, Dillon, and his alter-ego, Dillo, will stop at nothing to steamroll the competition, all while spouting off mispronounced Spanish.', 'Good', 'images/Dillo.jpg', NULL, NULL, '170 cm', '65 kg', NULL),
                                                                                                                     ('Ichor', 'Karl Cochlin', 'Karl Cochlin''s life has been marked by hardship ever since his father, the original Ichor, vanished without a trace. This pivotal moment defined Karl''s trajectory, compelling him to aspire to heroism greater than his father''s. However, Karl''s aspirations were stifled by his mother''s delicate condition, anchoring him to their modest country farmhouse. Everything changed when his Aunt Sally Anders arrived one fateful day, bearing a letter from the enigmatic Phillip Lineman, the esteemed Dean of Tetran Academy. With the opportunity of a lifetime now before him, Karl''s unwavering determination is fueled by the singular goal of surpassing his peers in both prowess and resilience.', 'Good', 'images/Ichor.jpg', NULL, NULL, '175 cm', '70 kg', NULL),
                                                                                                                     ('Wanderer', 'Hank Pebble', 'Henry <i>"Hank"</i> Pebble, is the reluctant scion of heroic lineage. With a heart as steadfast as the mountains and a mind teeming with untapped potential, Hank grapples with the weight of familial expectations and the allure of independence. As he embarks on his journey to Tetran Academy, Hanks towering stature and gentle demeanor belie the inner turmoil that churns within. With the legacy of his parents looming large, Hank stands at the precipice of greatness, poised to carve his own path amidst the stars. Little does he know that his destiny is greater than he could ever imagine.', 'Good', 'images/Wanderer.jpg', NULL, NULL, '185 cm', '80 kg', NULL),
                                                                                                                     ('SeaQueen', 'Serha Astrioc', 'Serha Astrioc, An enigmatic Sanhara (mutant), possesses a captivating allure that belies her turbulent past. With the ability to absorb the qualities of any ocean life for one hour at a time, and a heart as vast as the ocean depths she calls home, Serha navigates the treacherous waters of Plethios with grace and determination. As a mutant in a world that shuns her kind, Serha''s journey to Tetran Academy offers a glimmer of hope amidst the swirling currents of prejudice. With her keen intellect and unwavering spirit, Serha stands poised to make waves in the hallowed halls of Tetran Academy, her presence heralding a new era of acceptance and unity. While she pretends to be human while attending the academy, Serha embraces her heritage, and is proud to be a Plethian.', 'Good', 'images/Seaqueen.jpg', NULL, NULL, '170 cm', '60 kg', NULL),
                                                                                                                     ('Contra', 'Contessa Bontemundo', 'Contessa "Tess" Bontemundo may seem like the average mean girl, but she is so much more than that. As the only daughter of two prominent dramatic acting stars, Tess was handed a silver spoon from a young age. More accurately, she attracted silver spoons, and knives, and other metallic objects, terrifying many a nanny. It quickly became apparent that the girl''s "magnetic personality" was more than just an expression. With the extraordinary ability to control magnetic fields, Contessa is a powerhouse in almost any situation. Now a student at Tetran Academy, Tess is struggling to keep up with monsters such as Lance Talon and Exa Hershel. But she?s determined not to give up, no matter how many times she gets knocked down. And just maybe, she might be able to get Paul Wyvernel to notice her as more than just a friend. Wouldn?t that be nice?', 'Good', 'images/Contra.jpg', NULL, NULL, '165 cm', '55 kg', NULL),
                                                                                                                     ('X-A', 'Exa Hershel', 'Placeholder bio for X-A Hershel', 'Good', 'images/X-A.jpg', NULL, NULL, '175 cm', '70 kg', NULL),
                                                                                                                     ('FluxLight', 'Lux Deltari', 'Placeholder bio for Lux Deltari', 'Good', 'images/FluxLight.jpg', NULL, NULL, '170 cm', '60 kg', NULL),
                                                                                                                     ('Crushstone', 'Brock Pebble', 'Placeholder bio for Brock Pebble', 'Good', 'images/Crushstone.jpg', NULL, NULL, '190 cm', '90 kg', NULL),
                                                                                                                     ('Quickling', 'Jake Talon', 'bio', 'Good', 'images/Quickling.jpg', NULL, NULL, '180 cm', '75 kg', NULL),
                                                                                                                     ('Lady Mercury', 'Maybelle Stevens', 'bio', 'Good', 'images/Lady_Mercury.jpg', NULL, NULL, '165 cm', '55 kg', NULL),
                                                                                                                     ('Fyre', 'Fyre', 'bio', 'Evil', 'images/Fyre.jpg', NULL, NULL, '180 cm', '70 kg', NULL),
                                                                                                                     ('Galvin', 'Galvin Blacksteel', 'bio', 'Evil', 'images/Galvin.jpg', NULL, NULL, '175 cm', '65 kg', NULL),
                                                                                                                     ('Falinar', 'Falinar the Dragon', 'bio', 'Evil', 'images/Falinar.jpg', NULL, NULL, '200 cm', '150 kg', NULL),
                                                                                                                     ('Zylost', 'Zylost the ThunderBird', 'bio', 'Evil', 'images/Zylost.jpg', NULL, NULL, '180 cm', '80 kg', NULL),
                                                                                                                     ('Riff', 'Kevin Trevor', 'bio', 'Anti-Hero', 'images/Riff.jpg', NULL, NULL, '185 cm', '75 kg', NULL),
                                                                                                                     ('Barriguard', 'Fantasia Barrigaurde', 'bio', 'Other', 'images/Barrigaurd.jpg', NULL, NULL, '170 cm', '70 kg', NULL),
                                                                                                                     ('Gearshift', 'Adam Sheers', 'bio', 'Good', 'images/Gearshift.jpg', NULL, NULL, '175 cm', '65 kg', NULL),
                                                                                                                     ('Cyclonus', 'Tal-on(Thomas Talon)', 'bio', 'Good', 'images/Cyclonus.jpg', NULL, NULL, '180 cm', '75 kg', NULL);





-- Create Powers Table
CREATE TABLE Powers (
                        PowerID INT AUTO_INCREMENT PRIMARY KEY,
                        Description VARCHAR(255),
                        Explanation TEXT,
                        HeroId INT, -- New foreign key for the Hero table
                        FOREIGN KEY (HeroId) REFERENCES Hero(HeroId)
);

INSERT INTO Powers (description, explanation, HeroId) VALUES
                                                          ('Windchild', 'As a member of a rare type of Veltonian known as a WindChild, Lance can control and manipulate the air and wind currents around him with precision, allowing him to create powerful gusts of wind, generate tornadoes, and even fly.', 1),
                                                          ('Plasma Manipulation', 'Paul has the ability to generate and manipulate plasma, the fourth state of matter, which he can use to create intense heat, bright light, and powerful energy blasts.', 2),
                                                          ('Rage Transformation (Armadillo)', 'When angered or threatened, Dillon has the ability to transform into a powerful armadillo-like creature with enhanced strength, durability, and armored skin.', 3),
                                                          ('Golden Blood Manipulation', 'Karl possesses the unique ability to control and manipulate his own blood, which has a golden hue due to his Aeon heritage. He can use this ability for healing, enhanced physical abilities, and defensive purposes.', 4),
                                                          ('Acidic Sweat', 'Hank secretes a corrosive acid from his pores when threatened, which can burn through most materials and dissolve obstacles in his path.', 5),
                                                          ('Trait Absorption (Aquatic)', 'Serha can absorb the traits and abilities of any ocean life form she touches, gaining their characteristics, such as enhanced swimming abilities, underwater breathing, and camouflage.', 6),
                                                          ('Magnetic Control', 'Contessa has the power to control and manipulate magnetic fields, allowing her to move metallic objects with her mind, create magnetic force fields for defense, and even levitate herself.', 7),
                                                          ('Computer-Fast Calculations', 'Exa possesses an enhanced mental capacity that enables him to perform complex calculations and process information at incredible speeds, making him a formidable strategist and problem solver.', 8),
                                                          ('Nanomachine Disconnection', 'Exa has the ability to disrupt and deactivate nanomachines within his vicinity, rendering advanced technological systems and cybernetic enhancements useless against him.', 8),
                                                          ('Internet Access', 'Exa can wirelessly connect to and access information from the internet using his mind, allowing him to gather intelligence and access databases instantaneously.', 8),
                                                          ('Luminian Light Wielding', 'Lux can generate and manipulate luminian light, a powerful form of energy that he can use to create blinding flashes, energy beams, and protective barriers.', 9),
                                                          ('Stone skin', 'Brock possesses dense and durable skin that grants him enhanced durability and resistance to physical damage, making him nearly invulnerable to conventional weapons and attacks.', 10),
                                                          ('Super Strength', 'Brock possesses incredible physical strength, allowing him to lift heavy objects, punch through solid walls, and overpower opponents with ease.', 10),
                                                          ('Super Speed', 'Jake possesses the ability to move at superhuman speeds, allowing him to run faster than the eye can see, dodge bullets, and perform rapid strikes in combat.', 11),
                                                          ('Energy Projection', 'As the Carrier of Zylost, Jake has the ability to fire beams of pure Zytron Energy from his hands. Doing this seriously burns them, however', 11),
                                                          ('Azothian Symbiosis', 'Maybelle Stevens, also known as Lady Mercury, is bonded with an ancient Azothian demon, granting her enhanced physical abilities, demonic powers, and the ability to manipulate fire.', 12),
                                                          ('Fire Breath', 'Fyre, the villainous entity, possesses the ability to breathe intense flames from his mouth, capable of melting steel and incinerating objects in his path.', 13),
                                                          ('Steel Skin', 'Fyre has the power to transform his skin into an impenetrable armor-like substance, providing him with enhanced durability and protection against physical attacks.', 13),
                                                          ('Flight', 'Fyre can levitate and propel himself through the air at high speeds, granting him aerial mobility and an advantage in combat.', 13),
                                                          ('Computer fast brain', 'Fyre''s brain operates at incredibly fast speeds, enabling him to process information and make split-second decisions with unparalleled efficiency.', 13),
                                                          ('Azothian Symbiosis', 'As an Azothian Symbiote, Galvin is able to bond with other creatures. He feeds off thier excess bio-electricty, and in turn gives them extradionary abilities', 14),
                                                          ('Weapons training', 'Galvin is extensively trained in various forms of combat and proficient in the use of a wide array of weapons.', 14),
                                                          ('Fire Breath', 'Falinar can exhale a stream of intense flames from his mouth, capable of melting through most substances.', 15),
                                                          ('Horns', 'Falinar possesses large, razor-sharp horns on his head, which he can use as formidable weapons in close combat.', 15),
                                                          ('Claws', 'Falinar has sharp, retractable claws on his hands and feet, enabling him to slash through obstacles and adversaries with ease.', 15),
                                                          ('Plasma manipulation', 'Falinar has the ability to generate and manipulate plasma, allowing him to unleash powerful blasts of energy or create protective barriers.', 15),
                                                          ('Flight', 'Zylost has the ability to fly at high speeds using his powerful wings.', 16),
                                                          ('Talons', 'Zylost''s sharp talons can rend through most materials with ease, making him a formidable close-range combatant.', 16),
                                                          ('Lightning Breath', 'Zylost can unleash bolts of lightning from his mouth, capable of striking down enemies from a distance.', 16),
                                                          ('Energy Projection', 'Zylost can release bursts of energy from his body, which can be used for offensive or defensive purposes.', 16),
                                                          ('Azothian Symbiosis', 'Riff possesses a symbiotic connection with the Azothian energy, granting him enhanced physical abilities and regenerative powers.', 17),
                                                          ('Teleportation', 'Riff can instantly transport himself from one location to another, allowing for swift maneuvers and surprise attacks.', 17),
                                                          ('Sonic Manipulation', 'Riff can manipulate sound waves to create powerful sonic blasts or to disorient opponents.', 17),
                                                          ('Weapon Morphogenisis', 'Riff can transform parts of his body into various weapons, adapting to different combat situations.', 17),
                                                          ('Memory Shape', 'Barrigaurd has the ability to copy any armor or weapon she comes in contact with.', 18),
                                                          ('Weapons Experience', 'Barriguard possesses extensive experience with various weapons, allowing her to wield them with precision and skill.', 18),
                                                          ('Tech armor', 'Adam Sheers is equipped with advanced technological armor that provides enhanced protection and various capabilities.', 19),
                                                          ('Gear Gauntlets', 'Adam Sheers wears gauntlets with built-in gears that grant him enhanced strength and dexterity.', 19),
                                                          ('Gadgetry', 'Adam Sheers is skilled in using various gadgets and devices to aid him in combat and other tasks.', 19),
                                                          ('IQ', 'Adam possesses an exceptionally high IQ, granting him superior intelligence and problem-solving abilities.', 19),
                                                          ('Windchild', 'Cyclonus, also known as Tal-on, possesses the ability to manipulate air currents and control the wind, granting him the power of flight and the ability to create powerful gusts.', 20),
                                                          ('Healing Factor', 'Cyclonus has a remarkable regenerative ability that allows him to heal from injuries at an accelerated rate.', 20);


CREATE TABLE Equipment (
                           EquipmentId INT AUTO_INCREMENT PRIMARY KEY,
                           Name VARCHAR(255),
                           Description TEXT,
                           Images VARCHAR(255),
                           HeroId INT, -- New foreign key for the Hero table
                           FOREIGN KEY (HeroId) REFERENCES Hero(HeroId)
);

INSERT INTO Equipment (Name, Description, Images, HeroId) VALUES
                                                              ('Thundercloak', 'Cloak made from the feathers of Zylost herself.', 'images/Thundercloak.jpg', 1),
                                                              ('Flameblade', 'Symbol of Falinar, the Flameblade is a sword made of the purest fire in the cosmos.', 'images/Flameblade.jpg', 2),
                                                              ('Headwind', 'Symbol of Zylost, Headwind is the greatest spear ever crafted.', 'images/Headwind.jpg', 1),
                                                              ('Numaton Specs', 'Allow a person to see the aura of another. Only someone with tremendous reserves of power can use them, but the benefits far outweigh the cost.', 'images/Numaton_Specs.jpg', 1),
                                                              ('Thalasanite necklace', 'Allows her to summon her Thalassa companion, Gertie, when in water. The gem itself is highly valuable, and has unique properties.', 'images/_8a2ea05d-ef58-4ef4-90d0-1c82745effef.jpg', 6),
                                                              ('Zytron Earrings', 'A gift from Lance, these earrings are her most prized possession. Made of rare metal, and fitted with a mythical gemstone called Zytron crystals, these earrings are irreplaceable.', 'images/_e217dfab-3b94-4454-9ad0-0c79ca412d09.jpg', 6),
                                                              ('Tal-on''s Scarf (Destroyed)', 'A tattered scarf passed from father to son over two generations. It has no special abilities, but it has given the Talon men the courage to fight when they needed it most.', 'images/Red_scarf2.jpeg', 1),
                                                              ('Headwind (scarf form)', 'After merging with the tattered fragments of Lance''s original red scarf, Headwind can now take its shape in order to camouflage himself in public.', 'images/Blue_scarf.jpeg', 1),
                                                              ('Hex suit', 'Placeholder', 'images/Hex_suit.jpeg', 8),
                                                              ('Staff of Xaros', 'Placeholder', 'images/Staff_of_Xaros.jpg', 5),
                                                              ('Bio Guitar', 'After undergoing symbiosis with the Azothian mercenary Galvin Blacksteel, Riff gained the ability to give his equipment similar properties. His guitar is his favorite weapon, and its soundwaves can shatter concrete.', 'images/Galvin_guitar.jpeg', 17),
                                                              ('Fedora', 'Just something Wanderer Picked up on his travels. Useful for concealing his face around those he knew as Hank Pebble.', 'images/Fedora.jpeg', 5),
                                                              ('Bio Cloak', 'Similar to his guitar, his symbiote can take the form of a cloak in order to conceal Riff''s identity.', 'images/Galvin_cloak.jpeg', 17),
                                                              ('Nanobots', 'X-A''s body is made up of millions of these tiny robotic insects. She can disassemble herself at will, turning into a vicious swarm of robotic death.', 'images/Nanobot.jpeg', 8),
                                                              ('Leather Jacket', 'A gift from her father, Lux almost never takes it off.', 'images/_fd203878-b111-4dfa-a8cf-0dcf7b4b3fb4.jpg', 9),
                                                              ('Memory shape (Atlantean Edge)', 'A deep-sea armor Barrigaurd copied from an unlucky Plethian guardsmen. It enables her to breath underwater, as well as giving her great strength and unbreakable skin.', 'images/Atlantean_edge_armor.jpeg', 18),
                                                              ('Memory shape (Fast-Forward)', 'A time armor Barrigaurd copied from a displaced Chronaut. With it, she can summon her chrono saber, giving her the ability to literally slice through time.', 'images/Chrono_armor2.jpeg', 18),
                                                              ('Memory shape (Volcanic Vice)', 'A flame-based armor Barrigaurd copied from a Salandian knight she happened across. Capable of withstanding the heat of an active volcano, Barriguard has no fear of heat when wearing it.', 'images/Pyro_armor.jpeg', 18),
                                                              ('Memory shape (Barrigaurd)', 'Her family''s ancestral armor, any Barrigaurde is born knowing how to summon it. With it equipped, Barrigaurd can shield her allies while dealing devastating blows to the enemy with her autonomous lances.', 'images/Chrono_armor.jpeg', 18),
                                                              ('Hex-Shoes', 'A gift from his friend, and present wife, Amy Shira, the Hex-Shoes are a one of a kind invention that allow Brock to be light on his feet even when rocked up. Not only are they functional, but they look good too.', 'images/_e8130fd3-d3ba-4339-99e4-9c49447a5786.jpg', 10),
                                                              ('Shira Industries Flight Assistant Sun Visor', 'This brilliant invention created by the original Gearshift may look like a pair of ordinary sunglasses at first glance, but they are anything but. For one, they allow their user to see windspeed and direction, browse the internet, and predict their opponents next move, all hands free. For another, they make anyone who wears them instantly cool.', 'images/Sunglasses.jpeg', 11),
                                                              ('Shira Industries Flight Assistant Sun Visor', 'This brilliant invention created by the original Gearshift may look like a pair of ordinary sunglasses at first glance, but they are anything but. For one, they allow their user to see windspeed and direction, browse the internet, and predict their opponents next move, all hands free. For another, they make anyone who wears them instantly cool.', 'images/Sunglasses.jpeg', 20),
                                                              ('Tal-on''s Scarf (Destroyed)', 'While he may have only "bought" it in order to conceal his identity while be stranded on a planet not his own, Cyclonus''s red scarf quickly became a symbol of peace and justice known the world over. Villains learned to fear the sight of it flapping in the wind, but for everyone else, it signified justice.', 'images/Red_scarf.jpeg', 20),
                                                              ('Tal-on''s Duster', 'Like his scarf, his duster coat was never meant to be anything but a disguise. But as Tal-on embraced the role of hero more and more, it became a signature part of his image.', 'images/Duster.jpeg', 20),
                                                              ('Gust', 'A sentient sword known as a Windblade, Gust has had a storied past. It was mere circumstance that brought him and Tal together, but they have been inseparable since then. Even when Gust went dormant after the crash, Tal still made sure to check up on his friend every so often. Because that''s what true friends do.', 'images/Gust2.jpeg', 20),
                                                              ('Gust', 'As a sentient sword known as a Windblade, Gust had many interesting adventures before falling into Jake''s hands. As best friends, teammates, and mentally bonded to each other, Gust and Jake are a force to be reckoned with.', 'images/Gust.jpeg', 11),
                                                              ('Military Jacket', 'As Quickling, Jake never leaves home without his jacket. Made of indestructible threads, the jacket could go through hell and back, and still be unscathed.', 'images/Military_jacket.jpeg', 11),
                                                              ('Tal-on''s Scarf (Destroyed)', 'After inheriting the scarf from his dad, Jake wore it as part of his Quickling Costume. While it may not have held up as well as his jacket, it lasted long enough to pass it on to his son.', 'images/Red_scarf.jpeg', 11),
                                                              ('Bandoleir', 'Galvin always packs his bandolier for every eventuality, no matter how unlikely it may seem at the time.', 'images/Galvin_bandolier.jpeg', 14),
                                                              ('Symbiote Cycle (Mercury)', 'A union of her Symbiote, Mercury, and her Harley, The Symbiote Cycle serves as Miss Mercury''s primary means of transportation. Faster than a bullet train and twice as maneuverable, nothing on earth and possibly beyond can out race it.', 'images/Gust_cycle.jpeg', 12),
                                                              ('Gear Gauntlets', 'As an inventor without super-powers, Adam has to make his own special abilities. Using his massive brain and magic hands, he has stuffed every conceivable power he can replicate with technology into these mechanical masterpeices. Take that, Tony Stark.', 'images/Gear_gauntlets.jpeg', 19),
                                                              ('Gear Coat', 'Not nearly as impressive as his gauntlets, but still incredibly useful. With a built-in antimatter generator and force shield, Adam can enter fights against strong opponents without fear of injury.', 'images/Gear_coat.jpeg', 19);

CREATE TABLE Blog (
                      BlogId INT AUTO_INCREMENT PRIMARY KEY,
                      BlogTitle VARCHAR(255),
                      BlogContent TEXT,
                      DateTime TIMESTAMP,
                      HeroId INT,
                      FOREIGN KEY (HeroId) REFERENCES Hero(HeroId)
);


UPDATE Hero SET Emblem = 'images/openart-image_zQZl1fox_1710770536113_raw.png' WHERE HeroId = 1;
UPDATE Hero SET Emblem = 'images/_4bdaeb48-aa24-4d69-b725-75f8c4b2cd9f.jpg' WHERE HeroId = 2;
UPDATE Hero SET Emblem = 'images/_5fc43f07-70cb-45f5-a380-f047e43e759b.jpg' WHERE HeroId = 3;
UPDATE Hero SET Emblem = 'images/openart-image_JdCl_R9__1710770401088_raw.png' WHERE HeroId = 4;
UPDATE Hero SET Emblem = 'images/openart-image_aW-Jc88x_1710769534134_raw.png' WHERE HeroId = 5;
UPDATE Hero SET Emblem = 'images/_f09c2907-631a-4209-940c-3cd646aca28e.jpg' WHERE HeroId = 6;
UPDATE Hero SET Emblem = NULL WHERE HeroId = 7;
UPDATE Hero SET Emblem = 'images/_f399d715-118e-4283-8e44-a814317ef3c3.jpg' WHERE HeroId = 8;
UPDATE Hero SET Emblem = 'images/_c405917c-c823-4d8a-ac0a-1f3a856bbaeb.jpg' WHERE HeroId = 9;
UPDATE Hero SET Emblem = NULL WHERE HeroId = 10;
UPDATE Hero SET Emblem = 'images/openart-image_kJjs_nHB_1710722605273_raw.png' WHERE HeroId = 11;
UPDATE Hero SET Emblem = NULL WHERE HeroId = 12;
UPDATE Hero SET Emblem = NULL WHERE HeroId = 13;
UPDATE Hero SET Emblem = 'images/_34c88561-2f39-4657-a2c9-d2e4168bea69.jpg' WHERE HeroId = 14;
UPDATE Hero SET Emblem = NULL WHERE HeroId = 15;
UPDATE Hero SET Emblem = NULL WHERE HeroId = 16;
UPDATE Hero SET Emblem = 'images/Design.png' WHERE HeroId = 17;
UPDATE Hero SET Emblem = 'images/_48d5773b-6b61-468e-bf78-805f80bca6aa.jpg' WHERE HeroId = 18;
UPDATE Hero SET Emblem = 'images/openart-image_jIV8-1cs_1710794292800_raw.png' WHERE HeroId = 19;
UPDATE Hero SET Emblem = NULL WHERE HeroId = 20;


