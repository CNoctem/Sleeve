package bla.konishy.stripsleeve;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Coke {

    public static void main(String[] args) throws ImageProcessingException, IOException, MetadataException {
        Metadata metadata = ImageMetadataReader.readMetadata(new File("/home/ekovger/Pictures/nexus/DCIM/Camera/IMG_20190601_190917.jpg"));


        Set<String> wanteds = new HashSet<>();
        wanteds.add("Shutter Speed Value");
        wanteds.add("Aperture Value");
        wanteds.add("Exif Image Width");
        wanteds.add("Exif Image Height");
        wanteds.add("Exposure Mode");



        ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        for (Tag tag : directory.getTags()) {
            if (wanteds.contains(tag.getTagName())) {
                System.out.printf("%s / %s\n", directory.getName(), tag);
                System.out.println(tag.getTagName());
            }
        }

        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        int iso = directory.getInt(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT);
        float fnum = directory.getFloat(ExifSubIFDDirectory.TAG_FNUMBER);

        System.out.println(date);
        System.out.println(iso);
        System.out.println(fnum);

        System.out.println(directory.getFloat(ExifSubIFDDirectory.TAG_APERTURE));
        System.out.println(directory.getFloat(ExifSubIFDDirectory.TAG_35MM_FILM_EQUIV_FOCAL_LENGTH));
        System.out.println(directory.getFloat(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH) + " x " + directory.getFloat(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT));
        System.out.println(directory.getString(ExifSubIFDDirectory.TAG_SHUTTER_SPEED));
    }

}
