package com.assignment.extractingdata.Service;

import com.assignment.extractingdata.DAO.EmployeeRepository;
import com.assignment.extractingdata.Entity.Employee;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Value("${csv.file.path}")
    private String path;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public void saveData() {
        List<Employee> list = readData();
        employeeRepository.saveAll(list);
    }

    @Override
    public List<Employee> readData() {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        CSVReader reader = null;
        List<Employee> employees = new ArrayList<>();
        try {
            reader = new CSVReaderBuilder(new FileReader(path)).withCSVParser(parser).build();
            String[] line;
            while ((line = reader.readNext()) != null) {
            employees.add(new Employee(Integer.parseInt(line[0]), line[1], line[2]));
            }
        }
        catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        finally {

            try {
                if(reader!=null)
                    reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return employees;
    }

}


