# springstead-app-2
Hello and welcome to my inventory management application.  

The link to my video is 

In my application there are three textboxes in the left middle of the screen. The first one being a spot for the serial number. Where each serial number is unique and follows the pattern of number, -, three letters or numbers, -, three letter or numbers, -, and lastly three more numbers or letters. An example would be a-213-es3-ew9. And the second text box is for the name of the product. Here the restrictions for the box are that the characters must be withing 2 - 256. And the last box is for the value/price. And the requirements are that you must enter a USD amount which has to be positive. An example would be $12.32. After filling out all three of these boxes you can press the Add button and the inputs will be displayed in the table. Unless you did not satisfy the requirements, then an error message will pop up in the bottom of the screen telling what was wrong. If all three are satisfied then the item will go into the table and an array list. 

To remove an item from the table all you must do is select any row and press the delete button. This will cause the item to be removed from the table and from the array list. 

To clear the table all you must do is press the clear button. This will result in all the information being deleted from the array list and the table. 

If you want to search for an element in the table this can be achieved by searching by the serial number or the name of the product. This can be achieved by typing what you want to look up in the text box that corresponds with what you want to find. After typing in, press the search button and if the item is in the table it will show up as the only thing in the table. This is done by creating a blank array list and going through every single element until it finds the match. And if it finds the match it will add the infomration to the new array list and display it. To get all the elements back into the table all you must do is empty out the fields and then press the search button again. This will result in the table being full again. 

To edit any information in the table all you must do is click the area you want to edit, and it will allow you to edit. But this does not mean you can input whatever you want. The text field will only save if the criteria above has been matched for the corresponding field. This will change both the array list and the table. 

To sort any of the information in the application the user can click on the header of what they want to sort by. All three serial number, product and value can be sorted by.

And lastly there are two more buttons in the top left. These are used to load and save files. When pressing the load file all the information in the table and array list will be cleared. For loading and saving you have the option to use a TSV and have a txt file, a JSON file, or a HTML file. This application can export the result of the table to all three of these files or taking in an input and displaying it in the table on the application. 
