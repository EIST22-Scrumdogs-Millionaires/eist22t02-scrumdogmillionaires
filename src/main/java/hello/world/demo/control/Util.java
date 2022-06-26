package hello.world.demo.control;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import hello.world.demo.control.email.EmailServiceImpl;
import hello.world.demo.model.Reservation;
import hello.world.demo.model.Restaurant;
import hello.world.demo.model.Visitor;

public class Util {

        public static List<LocalTime> getLocalTimeList(LocalTime mon, LocalTime tue, LocalTime wen, LocalTime thu,
                        LocalTime fri, LocalTime sat, LocalTime sun) {
                List<LocalTime> times = new ArrayList<>();
                times.add(mon);
                times.add(tue);
                times.add(wen);
                times.add(thu);
                times.add(fri);
                times.add(sat);
                times.add(sun);
                return times;
        }

        public static String reservationMail(Visitor user, Reservation reservation) {
                String mailText = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                                +
                                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
                                +
                                "<head>\n" +
                                "<!--[if gte mso 9]>\n" +
                                "<xml>\n" +
                                "  <o:OfficeDocumentSettings>\n" +
                                "    <o:AllowPNG/>\n" +
                                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                                "  </o:OfficeDocumentSettings>\n" +
                                "</xml>\n" +
                                "<![endif]-->\n" +
                                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                                "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n"
                                +
                                "  <title></title>\n" +
                                "  \n" +
                                "    <style type=\"text/css\">\n" +
                                "      @media only screen and (min-width: 670px) {\n" +
                                "  .u-row {\n" +
                                "    width: 650px !important;\n" +
                                "  }\n" +
                                "  .u-row .u-col {\n" +
                                "    vertical-align: top;\n" +
                                "  }\n" +
                                "\n" +
                                "  .u-row .u-col-100 {\n" +
                                "    width: 650px !important;\n" +
                                "  }\n" +
                                "\n" +
                                "}\n" +
                                "\n" +
                                "@media (max-width: 670px) {\n" +
                                "  .u-row-container {\n" +
                                "    max-width: 100% !important;\n" +
                                "    padding-left: 0px !important;\n" +
                                "    padding-right: 0px !important;\n" +
                                "  }\n" +
                                "  .u-row .u-col {\n" +
                                "    min-width: 320px !important;\n" +
                                "    max-width: 100% !important;\n" +
                                "    display: block !important;\n" +
                                "  }\n" +
                                "  .u-row {\n" +
                                "    width: calc(100% - 40px) !important;\n" +
                                "  }\n" +
                                "  .u-col {\n" +
                                "    width: 100% !important;\n" +
                                "  }\n" +
                                "  .u-col > div {\n" +
                                "    margin: 0 auto;\n" +
                                "  }\n" +
                                "  }\n" +
                                "body {\n" +
                                "  margin: 0;\n" +
                                "  padding: 0;\n" +
                                "}\n" +
                                "\n" +
                                "table,\n" +
                                "tr,\n" +
                                "td {\n" +
                                "  vertical-align: top;\n" +
                                "  border-collapse: collapse;\n" +
                                "}\n" +
                                "\n" +
                                "p {\n" +
                                "  margin: 0;\n" +
                                "}\n" +
                                "\n" +
                                ".ie-container table,\n" +
                                ".mso-container table {\n" +
                                "  table-layout: fixed;\n" +
                                "}\n" +
                                "\n" +
                                "* {\n" +
                                "  line-height: inherit;\n" +
                                "}\n" +
                                "\n" +
                                "a[x-apple-data-detectors='true'] {\n" +
                                "  color: inherit !important;\n" +
                                "  text-decoration: none !important;\n" +
                                "}\n" +
                                "\n" +
                                "table, td { color: #000000; } a { color: #0000ee; text-decoration: underline; } @media (max-width: 480px) { #u_content_button_5 .v-size-width { width: 77% !important; } }\n"
                                +
                                "    </style>\n" +
                                "  \n" +
                                "  \n" +
                                "\n" +
                                "<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,700\" rel=\"stylesheet\" type=\"text/css\"><link href=\"https://fonts.googleapis.com/css?family=Raleway:400,700\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n"
                                +
                                "\n" +
                                "</head>\n" +
                                "\n" +
                                "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #c5e1a5;color: #000000\">\n"
                                +
                                "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                                "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                                "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #c5e1a5;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "  <tr style=\"vertical-align: top\">\n" +
                                "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                                +
                                "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #c5e1a5;\"><![endif]-->\n"
                                +
                                "    \n" +
                                "\n" +
                                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                                +
                                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 650px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f1f3f5;\">\n"
                                +
                                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
                                +
                                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px;\"><tr style=\"background-color: #f1f3f5;\"><![endif]-->\n"
                                +
                                "      \n" +
                                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"background-color: #ffffff;width: 650px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                                +
                                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 650px;display: table-cell;vertical-align: top;\">\n"
                                +
                                "  <div style=\"background-color: #ffffff;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                                +
                                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n"
                                +
                                "  \n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"0%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #c5e1a5;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                                +
                                "    <tbody>\n" +
                                "      <tr style=\"vertical-align: top\">\n" +
                                "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                                +
                                "          <span>&#160;</span>\n" +
                                "        </td>\n" +
                                "      </tr>\n" +
                                "    </tbody>\n" +
                                "  </table>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                                "  <tr>\n" +
                                "    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
                                "      \n" +
                                "      <img align=\"center\" border=\"0\" src=\"https://images.unlayer.com/projects/0/1655633097642-ratskeller-restaurant-kleiner.jpg\" alt=\"Hero Image\" title=\"Hero Image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 77%;max-width: 500.5px;\" width=\"500.5\"/>\n"
                                +
                                "      \n" +
                                "    </td>\n" +
                                "  </tr>\n" +
                                "</table>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n" +
                                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                                "    </div>\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: #c5e1a5\">\n" +
                                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 650px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f1f3f5;\">\n"
                                +
                                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
                                +
                                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #c5e1a5;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px;\"><tr style=\"background-color: #f1f3f5;\"><![endif]-->\n"
                                +
                                "      \n" +
                                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"width: 650px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                                +
                                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 650px;display: table-cell;vertical-align: top;\">\n"
                                +
                                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                                +
                                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n"
                                +
                                "  \n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "  <h1 style=\"margin: 0px; color: #c5e1a5; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Open Sans',sans-serif; font-size: 30px;\">\n"
                                +
                                "    <strong>Reservierung best&auml;tigt</strong>\n" +
                                "  </h1>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 50px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "        <div style=\"color: #5e5e5e; line-height: 170%; text-align: center; word-wrap: break-word;\">\n"
                                +
                                "            <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 16px; line-height: 27.2px;\">Hallo "
                                + user.getUsername() + ",</span></p>\n" +
                                "        <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 16px; line-height: 27.2px;\">Vielen Dank, dass Sie Scrumdog Millionaires verwenden. </span></p>\n"
                                +
                                "        <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 16px; line-height: 27.2px;\">Sie haben bei "
                                + RestaurantOverview.getRestaurantById(reservation.getRestaurant_id()).getName()
                                + " reserviert:</span></p>\n"
                                +
                                "        <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 16px; line-height: 27.2px;\">Tisch "
                                + reservation.getTable().getId() + " &nbsp;am " + reservation.getDate() + " um "
                                + reservation.getTime() + " <br />Reservierungsname: "
                                + reservation.getUser().getUsername() + "<br />Best&auml;tigungsnummer: "
                                + reservation.getId() + "</span></p>\n" +
                                "          </div>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "  <h1 style=\"margin: 0px; color: #000000; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Open Sans',sans-serif; font-size: 19px;\">\n"
                                +
                                "    F&uuml;gen Sie den Termin zu Ihrem Kalender hinzu: "
                                + "<a href=\""
                                + EmailServiceImpl.generateCalendarLink(reservation,
                                        RestaurantOverview.getRestaurantById(reservation.getRestaurant_id()))
                                + "\">Kalender-Event</a>"
                                + "\n"
                                +
                                "  </h1>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                                +
                                "    <tbody>\n" +
                                "      <tr style=\"vertical-align: top\">\n" +
                                "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                                +
                                "          <span>&#160;</span>\n" +
                                "        </td>\n" +
                                "      </tr>\n" +
                                "    </tbody>\n" +
                                "  </table>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n" +
                                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                                "    </div>\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                                +
                                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 650px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f1f3f5;\">\n"
                                +
                                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
                                +
                                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px;\"><tr style=\"background-color: #f1f3f5;\"><![endif]-->\n"
                                +
                                "      \n" +
                                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"width: 650px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                                +
                                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 650px;display: table-cell;vertical-align: top;\">\n"
                                +
                                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                                +
                                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n"
                                +
                                "  \n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 50px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "  <div style=\"color: #5e5e5e; line-height: 170%; text-align: center; word-wrap: break-word;\">\n"
                                +
                                "    <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 16px; line-height: 27.2px;\">Doch schon etwas Anderes vor? Sie k&ouml;nnen Ihre Reservierung bis zu 12h vorher stornieren, indem Sie auf den Button klicken. Es ist kinderleicht.</span></p>\n"
                                +
                                "  </div>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "  <h1 style=\"margin: 0px; color: #000000; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Open Sans',sans-serif; font-size: 22px;\">\n"
                                +
                                "    Ich m&ouml;chte meine Reservierung stornieren: localhost:8080/reservations/"
                                                        + reservation.getId() + "/" + reservation.getCancelSecretKey() +
                                "  </h1>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n"
                                +
                                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                                "    </div>\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                                +
                                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 650px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #6e6e6e;\">\n"
                                +
                                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
                                +
                                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px;\"><tr style=\"background-color: #6e6e6e;\"><![endif]-->\n"
                                +
                                "      \n" +
                                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"background-color: #c5e1a5;width: 650px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                                +
                                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 650px;display: table-cell;vertical-align: top;\">\n"
                                +
                                "  <div style=\"background-color: #c5e1a5;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                                +
                                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n"
                                +
                                "  \n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "<div align=\"center\">\n" +
                                "  <div style=\"display: table; max-width:93px;\">\n" +
                                "  <!--[if (mso)|(IE)]><table width=\"93\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:93px;\"><tr><![endif]-->\n"
                                +
                                "  \n" +
                                "    \n" +
                                "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 15px;\" valign=\"top\"><![endif]-->\n"
                                +
                                "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 15px\">\n"
                                +
                                "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                                +
                                "        <a href=\"https://github.com/EIST22-Scrumdogs-Millionaires\" title=\"GitHub\" target=\"_blank\">\n"
                                +
                                "          <img src=\"https://cdn.tools.unlayer.com/social/icons/rounded-black/github.png\" alt=\"GitHub\" title=\"GitHub\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                                +
                                "        </a>\n" +
                                "      </td></tr>\n" +
                                "    </tbody></table>\n" +
                                "    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                                "    \n" +
                                "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 0px;\" valign=\"top\"><![endif]-->\n"
                                +
                                "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\n"
                                +
                                "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                                +
                                "        <a href=\"mailto:scrumdogmillionairies@yahoo.de\" title=\"Email\" target=\"_blank\">\n"
                                +
                                "          <img src=\"https://cdn.tools.unlayer.com/social/icons/rounded-black/email.png\" alt=\"Email\" title=\"Email\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                                +
                                "        </a>\n" +
                                "      </td></tr>\n" +
                                "    </tbody></table>\n" +
                                "    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                                "    \n" +
                                "    \n" +
                                "    <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n" +
                                "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                                +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                                +
                                "        \n" +
                                "  <div style=\"color: #ffffff; line-height: 190%; text-align: center; word-wrap: break-word;\">\n"
                                +
                                "    <p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 14px; line-height: 26.599999999999998px; color: #ffffff;\">Sie erhalten diese E-Mail, da Sie einen Tisch &uuml;ber Scrumdog Millionaires reserviert haben.</span></p>\n"
                                +
                                "<p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 14px; line-height: 26.599999999999998px; color: #ffffff;\">&copy;2022 Scrumdog Millionaires | Boltzmannstra&szlig;e 3, 85748 Garching bei M&uuml;nchen</span></p>\n"
                                +
                                "  </div>\n" +
                                "\n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>\n" +
                                "\n" +
                                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                                "    </div>\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "\n" +
                                "\n" +
                                "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                                "    </td>\n" +
                                "  </tr>\n" +
                                "  </tbody>\n" +
                                "  </table>\n" +
                                "  <!--[if mso]></div><![endif]-->\n" +
                                "  <!--[if IE]></div><![endif]-->\n" +
                                "</body>\n" +
                                "\n" +
                                "</html>";
                String mailText1 = "Bitte bestätigen Sie Ihre Reservierung, " + user.getUsername() + "\n Tisch "
                                + reservation.getTable().getId() + " für " + reservation.getTable().getSeats()
                                + " Person(en) \n"
                                + reservation.getDate() + " um " + reservation.getTime() + ". \n" + "Reservierungsname:"
                                + reservation.getUser().getUsername() + "\n Bestätigungsnummer: " + reservation.getId()
                                + "\n"
                                + "Bitte bestätigen Sie Ihre Reservierung, indem Sie auf den folgenden Link klicken: LINK EINFÜGEN"
                                + "\n Wir freuen uns auf Sie!" + "\n\n"
                                + "Doch schon etwas Anderes vor? Sie können Ihre Reservierung bis zu 12h vorher stornieren, indem Sie auf den folgenden Link klicken. Es ist kinderleicht. Jetzt Reservierung stornieren:  localhost:8080/reservations/"
                                + reservation.getId() + "/" + reservation.getCancelSecretKey();

                return mailText;
        }

        public static String confirmMail(Visitor user, Reservation reservation, Restaurant restaurant) {
                String mailText = " Ihre Reservierung ist bestätigt, " + user.getUsername()
                                + "!\n Vielen Dank dass Sie bei " + restaurant.getName() + "reserviert haben. \n Tisch "
                                + reservation.getTable().getId() + " für" + reservation.getTable().getSeats()
                                + " Person(en) \n"
                                + reservation.getDate() + " um " + reservation.getTime() + ". \n"
                                + "Reservierungsname: "
                                + reservation.getUser().getUsername() + "\n Bestätigungsnummer: " + reservation.getId()
                                + "\n\n"
                                + "Wir freuen uns auf Sie!" + "\n\n"
                                + "Doch schon etwas Anderes vor? Sie können Ihre Reservierung bis zu 12h vorher stornieren, indem Sie auf den folgenden Link klicken. Es ist kinderleicht. Jetzt Reservierung stornieren:  localhost:8080/reservations/"
                                + reservation.getId() + "/" + reservation.getCancelSecretKey()
                                + " \n\n Was Sie vor Ihrem Besuch wissen sollten\n" +
                                "Der Tisch wird bis zu 15 Minuten nach Ihrer Reservierungszeit für Sie freigehalten. Bitte rufen Sie uns an, wenn Sie sich um mehr als 15 Minuten verspäten.\n"
                                +
                                "Der Tisch wird 2 Stunden für Sie reserviert.";
                return mailText;
        }

}
