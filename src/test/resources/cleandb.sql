-- Drop tables if they exist
DROP TABLE IF EXISTS Powers;
DROP TABLE IF EXISTS Hero;

-- Create Hero Table
CREATE TABLE Hero (
                      HeroID INT AUTO_INCREMENT PRIMARY KEY,
                      CodeName VARCHAR(255),
                      RealName VARCHAR(255),
                      Bio TEXT,
                      Alignment VARCHAR(255)
);

-- Insert Data into Hero table
INSERT INTO Hero (CodeName, RealName, Bio, Alignment) VALUES
                                                          ('Windchild', 'Lance Talon', 'Placeholder bio for Lance Talon', 'Good'),
                                                          ('Falinex', 'Paul Wyvernel', 'Placeholder bio for Paul Wyvernel', 'Good'),
                                                          ('Dillo', 'Dillon Arma', 'Placeholder bio for Dillon Arma', 'Good'),
                                                          ('Ichor', 'Karl Cochlin', 'Placeholder bio for Karl Cochlin', 'Good'),
                                                          ('Wanderer', 'Hank Pebble', 'Placeholder bio for Hank Pebble', 'Good'),
                                                          ('SeaQueen', 'Serha Astrioc', 'Placeholder bio for Serha Astrioc', 'Good'),
                                                          ('Contra', 'Contessa Bontemundo', 'Placeholder bio for Contessa Bontemundo', 'Good'),
                                                          ('Exa', 'X-A Hershel', 'Placeholder bio for X-A Hershel', 'Good'),
                                                          ('MoodLight', 'Lux Deltari', 'Placeholder bio for Lux Deltari', 'Good'),
                                                          ('Crushstone', 'Brock Pebble', 'Placeholder bio for Brock Pebble', 'Good'),
                                                          ('Quickling', 'Jake Talon', 'bio', 'Good'),
                                                          ('Lady Mercury', 'Maybelle Stevens', 'bio', 'Good'),
                                                          ('Fyre', 'Fyre', 'bio', 'Evil'),
                                                          ('Galvin', 'Galvin Blacksteel', 'bio', 'Evil'),
                                                          ('Falinar', 'Falinar the Dragon', 'bio', 'Evil'),
                                                          ('Zylost', 'Zylost the ThunderBird', 'bio', 'Evil'),
                                                          ('Riff', 'Kevin Trevor', 'bio', 'Anti-Hero'),
                                                          ('Barriguard', 'Fantasia Barrigaurde', 'bio', 'Other'),
                                                          ('Din-Gildeth', 'Choreas Cochlin', 'bio', 'Other'),
                                                          ('Din-Shaden', 'Unknown', 'bio', 'Evil');


-- Create Powers Table
CREATE TABLE Powers (
                        PowerID INT AUTO_INCREMENT PRIMARY KEY,
                        Description VARCHAR(255),
                        HeroID INT, -- New foreign key for the Hero table
                        FOREIGN KEY (HeroID) REFERENCES Hero(HeroID)
);

-- Insert Data into Powers table
INSERT INTO Powers (Description, HeroID) VALUES
                                             ('Windchild', 1),
                                             ('Plasma Manipulation', 2),
                                             ('Rage Transformation (Armadillo)', 3),
                                             ('Golden Blood Manipulation', 4),
                                             ('Acidic Sweat', 5),
                                             ('Trait Absorption (Aquatic)', 6),
                                             ('Magnetic Control', 7),
                                             ('Computer-Fast Calculations', 8),
                                             ('Nanomachine Disconnection', 8),
                                             ('Internet Access', 8),
                                             ('Luminian Light Wielding', 9),
                                             ('Stone skin', 10),
                                             ('Super Strength', 10),
                                             ('Super Speed', 11),
                                             ('Energy Projection', 11),
                                             ('Azothian Symbiosis', 12),
                                             ('Fire Breath', 13),
                                             ('Steel Skin', 13),
                                             ('Flight', 13),
                                             ('Computer fast brain', 13),
                                             ('Azothian Symbiosis', 14),
                                             ('Weapons training', 14),
                                             ('Fire Breath', 15),
                                             ('Horns', 15),
                                             ('Claws', 15),
                                             ('Plasma manipulation', 15),
                                             ('Flight', 16),
                                             ('Talons', 16),
                                             ('Lightning Breath', 16),
                                             ('Energy Projection', 16),
                                             ('Azothian Symbiosis', 17),
                                             ('Teleportation', 17),
                                             ('Sonic Manipulation', 17),
                                             ('Weapon Morphogenisis', 17),
                                             ('Manifest Armor from Memories', 18),
                                             ('Weapons Experience', 18),
                                             ('Weapons Exprience', 19),
                                             ('Assassin Skills', 19),
                                             ('Weapons Experience', 20),
                                             ('Assassin Skills', 20),
                                             ('Golden Blood Manipulation', 19);