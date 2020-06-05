package campground_data;

import java.util.ArrayList;

public class AccommodationHelper
{
        private static ArrayList<Accommodation> accommodations;
        private DatabaseFile dbFile;

        public AccommodationHelper()
        {
                dbFile = new DatabaseFile();
                this.accommodations = dbFile.readPlots();
        }

        public boolean addAccommodation(Accommodation newAccommodation)
        {
                this.accommodations.add(newAccommodation);
                dbFile.saveRecords(accommodations);
                return this.accommodations.contains(newAccommodation);
        }

        public void removeAccommodation(Accommodation accommodation)
        {
                AccommodationHelper.accommodations.remove(accommodation);
        }

        public ArrayList<Accommodation> getAccommodationList()
        {
            return this.accommodations;
        }

        public Cabin searchCabin(int AccommodationID)
        {
                Cabin plotToReturn = null;
                for (Accommodation accommodation : AccommodationHelper.accommodations)
                {
                        if (accommodation.getAccommodationID() == AccommodationID)
                        {
                                plotToReturn = (Cabin) accommodation;
                        }
                }

                return plotToReturn;
        }

        public Site searchSite(int AccommodationID)
        {
                Site plotToReturn = null;
                for (Accommodation accommodation : AccommodationHelper.accommodations)
                {
                        if (accommodation.getAccommodationID() == AccommodationID)
                        {
                                plotToReturn = (Site) accommodation;
                        }
                }

                return plotToReturn;
        }

        public Accommodation searchAccommodation(int AccommodationID)
        {
                Accommodation accommodationToReturn = null;
                for (Accommodation accommodation : AccommodationHelper.accommodations)
                {
                        if (accommodation.getAccommodationID() == AccommodationID)
                        {
                                accommodationToReturn = accommodation;
                        }
                }

                return accommodationToReturn;
        }
}

