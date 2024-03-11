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

-- Create Powers Table
CREATE TABLE Powers (
                        PowerID INT AUTO_INCREMENT PRIMARY KEY,
                        Description VARCHAR(255),
                        HeroID INT,
                        FOREIGN KEY (HeroID) REFERENCES Hero(HeroID)
);
