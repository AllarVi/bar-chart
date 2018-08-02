# Application to display data in bar chart format

## Running the app

Find the release folder and execute the following in terminal:

```
$ java -jar bar_chart.jar test.csv
```

## Description

-   start-up arguments

You have to create a graphical application with java (javaFX, Swing), which will
read data on start-up from an input file containing information about
refuelings.

User can define data file as an input parameter, for example a command line
parameter, dialog etc.

Data file contains information about refuelings for one single year.

-   validations

All entries in the data file must be positive values, negative values should be
validated as errors.

Application must have basic error handling against broken input etc.

-   combo box

User can select the fuel type from a combo box.

Sum and group all refuelings by month and filter by user selected gasoline type
on a bar chart.

-   visuals

There should be always a bar for each month on the chart (always 12 bars).

If some month does not have any refuelings, then the bar chart bar value is
0.000.

-   additional combo box value

Additionally to the different gasoline values from the data file there has to be
value "all" in the combo box.

Selecting the value "all" will sum all different gasoline types to one bar,
grouped by month.

For example if user spent 5.5 € for "95" in June and 25 € for "98" then the
selecting "all" from filter would display value 30.500 € for the June bar in the
application.

-   visuals: bar values

Application should be able to display the money spent on a bar chart for a
single fuel type, writing the amount and the month on every bar.

If there are no refuelings, the month bar and value 0.000 must still be visible.

Amounts should be displayed 3 digits after comma. 

-   visuals: coloring

On every bar chart the bars with maximum values must be colored red, the bars
with minimum values must be colored green and all others yellow.

Multiple bars with the peak values must have the same color.

## Bonus

You can add a feature which makes the application reload the data when the file
is changed on the disk.

## Technical requirements

use java version 8 or higher
for testing use JUnit
any other libraries are not allowed

## Example data structure

0..n rows.

## Data format

fuelName|fuelPrice|fuelAmount|refuellingDate

## Legend

fuelName - a string
fuel fuelPrice - can be with . or , (1.345; 1,345)
fuel fuelAmount - can be with . or , (50.53; 50,53)
refuellingDate - format is dd.mm.yyyy

## Example data

```csv
98|1.319|50.56|01.01.2016
98|1.319|45,32|15.01.2016
95|1.21|30.4|02.02.2016
98|1.319|50.30|09.02.2016
98|1.392|45.25|11.03.2016
95|1.319|5.00|01.04.2016
D|1.219|5.00|01.02.2016
E85|0.95|15,12|12.11.2016
```

## Publishing

Upload your code to public version control repository, for example free GitHub
repository.

