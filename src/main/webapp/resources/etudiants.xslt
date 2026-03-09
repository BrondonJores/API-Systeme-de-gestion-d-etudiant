<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!-- Transformation en HTML -->
    <xsl:output method="html" indent="yes"/>
    
    <xsl:template match="/etudiants">
        <html>
            <head>
                <title>Liste des étudiants</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f9f9f9;
                        color: #333;
                        padding: 20px;
                    }
                    h2 {
                        color: #1a73e8;
                    }
                    table {
                        border-collapse: collapse;
                        width: 100%;
                        max-width: 800px;
                        margin-top: 20px;
                        box-shadow: 0 0 10px rgba(0,0,0,0.1);
                    }
                    th, td {
                        border: 1px solid #ddd;
                        padding: 10px;
                        text-align: left;
                    }
                    th {
                        background-color: #1a73e8;
                        color: white;
                    }
                    tr:nth-child(even) {
                        background-color: #f2f2f2;
                    }
                    tr:hover {
                        background-color: #e0f0ff;
                    }
                </style>
            </head>
            <body>
                <h2>Liste des étudiants</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Téléphone</th>
                        <th>Adresse</th>
                    </tr>
                    <!-- Boucle sur chaque étudiant -->
                    <xsl:for-each select="etudiant">
                        <tr>
                            <td><xsl:value-of select="ID"/></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="surname"/></td>
                            <td><xsl:value-of select="telephone"/></td>
                            <td><xsl:value-of select="adresse"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
