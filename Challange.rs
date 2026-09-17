fn main()
{
    for i in 1000..2500{
    let mut num = i;
    let mut stack = Vec::new();

    while num > 0 {
        stack.push(num % 10);
        num /= 10;
    }
    stack.reverse(); 

    let quad = i*4;
    let mut new = 0;
    for i in (0..3).rev(){
        if let Some(digit) = stack.pop() {
            new += digit * 10_i32.pow(i as u32);
        }
    }
    if new == quad{
        print!("{}",new);
    }
    }
}